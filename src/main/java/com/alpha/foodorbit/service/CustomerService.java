package com.alpha.foodorbit.service;


import com.alpha.foodorbit.dto.CustomerReqDto;
import com.alpha.foodorbit.entities.Customer;
import com.alpha.foodorbit.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public void adding(CustomerReqDto customerReqDto) {
        Customer customer = new Customer();
         customer.setName(customerReqDto.getName());
         customer.setMobno(customerReqDto.getMobno());
         customer.setMailid(customerReqDto.getMailid());
         customer.setGender(customerReqDto.getGender());
         customerRepository.save(customer);
    }

    public void deleteCustomer(long mobno) {
       Customer c= customerRepository.findByMobno(mobno);
       customerRepository.delete(c);

    }

    public Customer findCustomer(long mobno) {
        return  customerRepository.findByMobno(mobno);



    }
}
