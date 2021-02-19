package th.ac.ku.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class BankAccountService {
    private List<BankAccount> bankAccountList;

    @Autowired
    private CustomerService customerService;

    @PostConstruct
    public void postConstruct() {
        this.bankAccountList = new ArrayList<>();
    }

    public void createBankAccount(BankAccount bankAccount) {
        bankAccountList.add(bankAccount);
    }

    public List<BankAccount> getBankAccountList() {
        return new ArrayList<>(this.bankAccountList);
    }

    public Customer findCustomer(int id) {
        for (Customer customer : customerService.getCustomers()) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    public Customer checkCustomer(int customerId) {
        Customer storedCustomer = findCustomer(customerId);
        if (storedCustomer != null) {
            return storedCustomer;
        }
        return null;
    }
}
