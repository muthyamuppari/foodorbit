package com.alpha.foodorbit.service;


import com.alpha.foodorbit.dto.CustomerReqDto;
import com.alpha.foodorbit.entities.CartItem;
import com.alpha.foodorbit.entities.Customer;
import com.alpha.foodorbit.entities.Item;
import com.alpha.foodorbit.repository.CartItemRepository;
import com.alpha.foodorbit.repository.CustomerRepository;
import com.alpha.foodorbit.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

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
}
