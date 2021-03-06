package th.ac.ku.atm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.atm.model.BankAccount;
import th.ac.ku.atm.service.BankAccountService;

@Controller
@RequestMapping("/bankaccount")
public class BankAccountController {

    @Autowired
    BankAccountService bankAccountService;

//    BankAccountController(BankAccountService bankAccountService) {
//        this.bankAccountService = bankAccountService;
//    }

    @PostMapping
    public String openBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
        bankAccountService.openBankAccount(bankAccount);
        model.addAttribute("allBankAccounts",bankAccountService.getAllBankAccounts());
        return "redirect:/bankaccount";
    }

    @GetMapping
    public String getBankAccount(Model model) {
        model.addAttribute("allBankAccounts", bankAccountService.getAllBankAccounts());
        return "/bankaccount";
    }

//    @PostMapping
//    public String createBankAccount(@ModelAttribute BankAccount bankAccount, Model model) {
//        Customer matchingCustomer = bankAccountService.findCustomer(bankAccount.getCustomerId());
//        if (matchingCustomer != null) {
//            bankAccountService.createBankAccount(bankAccount);
//            model.addAttribute("bankaccount", bankAccountService.getBankAccounts());
//        }
//        return "bankaccount";
//    }

    @GetMapping(path = "/edit/{id}")
    public String getEditBankAccountPage(@PathVariable int id, Model model) {
        BankAccount account = bankAccountService.getBankAccount(id);
        model.addAttribute("bankAccount", account);
        return "bankaccount-edit";
    }

    @PostMapping(path = "/edit/{id}")
    public String editAccount(@PathVariable int id,
                              @ModelAttribute BankAccount bankAccount,
                              Model model) {
        bankAccountService.editBankAccount(bankAccount);
        model.addAttribute("allBankAccount",bankAccountService.getAllBankAccounts());
        return "redirect:/bankaccount";
    }
}
