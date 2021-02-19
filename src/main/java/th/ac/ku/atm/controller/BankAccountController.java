package th.ac.ku.atm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.model.Customer;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }

    @GetMapping
    public String getBankAccountPage(Model model) {
        model.addAttribute("bankAccountList", bankAccountService.getBankAccountList());
        return "bankaccount";
    }

    @PostMapping
    public String createBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        Customer matchingCustomer = bankAccountService.checkCustomer(bankAccount.getCustomerId());
        if (matchingCustomer != null) {
            bankAccountService.createBankAccount(bankAccount);
            model.addAttribute("bankAccountList", bankAccountService.getBankAccountList());
        }
        return "bankaccount";
    }
}