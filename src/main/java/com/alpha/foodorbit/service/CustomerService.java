package com.alpha.foodorbit.service;


import com.alpha.foodorbit.dto.CustAddressReqDto;
import com.alpha.foodorbit.dto.CustomerReqDto;
import com.alpha.foodorbit.entities.*;
import com.alpha.foodorbit.exception.CustomerNotFound;
import com.alpha.foodorbit.exception.DifferentRestaurantItem;
import com.alpha.foodorbit.repository.*;
import com.alpha.foodorbit.special.DistanceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
  @Autowired
  private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private PaymentRepository paymentRepository;

    public void adding(CustomerReqDto customerReqDto) {
        Customer customer = new Customer();
         customer.setName(customerReqDto.getName());
         customer.setMobno(customerReqDto.getMobno());
         customer.setMailid(customerReqDto.getMailid());
         customer.setGender(customerReqDto.getGender());
         customerRepository.save(customer);
    }

    public void deleteCustomer(long mobno) {
       Customer c= customerRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("Customer not found"));
       customerRepository.delete(c);

    }

    public Customer findCustomer(long mobno) {
        return  customerRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("Customer not found"));



    }

    //

    public void addtocart(long mobno, int itemid, int quantity) {
        Customer customer=customerRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("Customer not found"));
       Item item =itemRepository.findById(itemid).orElseThrow(()->new RuntimeException("Item not found"));

        CartItem c1=new CartItem();
        c1.setQuantity(quantity);
        c1.setItem(item);
        c1.setCustomer(customer);
        c1.setRestaurant(item.getRestaurant());
        customer.getCartItems().add(c1);
        cartItemRepository.save(c1);


    }

    public Customer saveCustomer(CustomerReqDto dto) {

        Customer customer=new Customer();
        customer.setName(dto.getName());
        customer.setMobno(dto.getMobno());
        customer.setMailid(dto.getMailid());
        customer.setGender(dto.getGender());


        //new
//        Address address=new Address();
//
//        Map response=restTemplate.getForObject("https://us1.locationiq.com/v1/reverse?key=pk.5038d98b114a8653a2d8716f69a70c50"
//                + "&lat="+dto.getAddresses().getLocationCordinate().getLatitude() +
//                "&lon="+dto.getAddresses().getLocationCordinate().getLongitute()+ "&format=json", Map.class
//        );
//
//        Map add=(Map) response.get("address");
//        address.setPincode((String) add.get("postcode"));
//        address.setCity((String) add.get("city"));
//        address.setCountry((String) add.get("country"));
//        address.setState((String) add.get("state"));
//        address.setStreet((String) add.get("neighbourhood"));
//        address.setFlatNumber(dto.getAddresses().getFlatNumber());
//        address.setBuildingName(dto.getAddresses().getBuildingName());
//        address.setAddressType(dto.ge);

        List<Address> addressList =new ArrayList<>();
        for(CustAddressReqDto adto: dto.getAddresses()){
            Address address=new Address();
            address.setFlatNumber(adto.getFlatNumber());
            address.setBuildingName(adto.getBuildingName());
            address.setStreet(adto.getStreet());
            address.setCity(adto.getCity());
            address.setState(adto.getState());
            address.setPincode(adto.getPincode());
            address.setAddressType(adto.getAddressType());
            address.setDefault(adto.getIsDefault());
            addressList.add(address);

        }
        customer.setAddress(addressList);
       return customerRepository.save(customer);
    }


    public CartItem addtocartt(long mobno, int itemid, int quantity) {
         Customer customer=customerRepository.findByMobno(mobno).orElseThrow(()->new RuntimeException("Customer not found"));
        Item item = itemRepository.findById(itemid).orElseThrow(() -> new RuntimeException("Item not found"));

         List<CartItem> cart=customer.getCartItems();
        if(cart.isEmpty()){
            CartItem cartItem=new CartItem(item,quantity);
             cartItem.setCustomer(customer);
             cartItem.setRestaurant(item.getRestaurant());
             cart.add(cartItem);
             customerRepository.save(customer);
             return cartItem;


        }else{

            Restaurant existingRestaurant = cart.get(0).getRestaurant();
            Restaurant newRestaurant=item.getRestaurant();
           if(! ((existingRestaurant.getId()) == (newRestaurant.getId()) ) ) {
               throw new DifferentRestaurantItem("Cannot add item from different restaurant");
           }
               //if item already present
               Optional<CartItem> existingItem=cart.stream().filter(ci->ci.getItem().getId()==itemid)
                           .findFirst();
               if(existingItem.isPresent()){
                   CartItem cartItem=existingItem.get();
                   cartItem.setQuantity(cartItem.getQuantity()+ quantity);
                   customerRepository.save(customer);
                   return cartItem;
               }
               //new Item from same restaurant
                   CartItem cartItem=new CartItem(item,quantity);
                   cartItem.setCustomer(customer);
                   cartItem.setRestaurant(item.getRestaurant());
                   cart.add(cartItem);
            customerRepository.save(customer);
                   return cartItem;

           }


        }

    public List<CartItem> getAllCart(long mobno) {
        Customer customer = customerRepository.findByMobno(mobno).orElseThrow(() -> new RuntimeException("Customer not found"));
        List<CartItem> cartItems = customer.getCartItems();
        return  cartItems;


    }

    public void placingOrder(long mobno, String paymentType, String addressType, String specialRequest) {
        Customer customer = customerRepository.findByMobno(mobno).orElseThrow(() -> new CustomerNotFound("Cust not found"));

        if(customer.getCartItems().isEmpty()){
            throw new RuntimeException("Cart is empty");
        }
        Order order=new Order();
        order.setCustomer(customer);
        order.setStatus("Placed");

        Restaurant restaurant = customer.getCartItems().get(0).getItem().getRestaurant();
         order.setRestaurant(restaurant);
           Address pickupAddress=restaurant.getAddress();
         order.setPickupAddress(pickupAddress);
         Address delivAddress=null;
         for(Address a:customer.getAddress()){
             if(a.getAddressType().equals(addressType)){
                 delivAddress=a;
             }
         }
        order.setDeliveryAddress(delivAddress);

         order.setSpecialRequest(specialRequest);
         order.setDeliveryInstructions("Make it Spicy");
         order.setDiscount(0);
         order.setCoupon(null);
         order.setDeliveryPartner(null);
         order.setDate(LocalDateTime.now());




         //Distance
        double distance= DistanceUtil.calculateDistance(pickupAddress.getLatitude(),pickupAddress.getLongitude()
        ,delivAddress.getLatitude(),delivAddress.getLongitude());

         order.setDistance(distance);
         double delivery_charge=0;
         if(distance>2){
             delivery_charge= (distance-2)*10;
         }
         delivery_charge=Math.round(delivery_charge);
         double cost=0;

         for( CartItem c:customer.getCartItems()){

             cost=cost+(c.getItem().getPrice()*c.getQuantity());
                     }
        double FinalCost= cost + delivery_charge + restaurant.getPackagingFees();

         order.setCost(FinalCost);

          Payment payment=new Payment();
          payment.setAmount(FinalCost);
          payment.setType(paymentType);
          if(paymentType.equalsIgnoreCase("COD")){
              payment.setStatus("Pending");
          }else{
              payment.setStatus("Paid");
          }
          order.setPayment(payment);
           payment.setOrder(order);


        SecureRandom random=new SecureRandom();
        int otp= 1000 + random.nextInt(9000);
        order.setOtp(otp);


         orderRepository.save(order);

    }


}

