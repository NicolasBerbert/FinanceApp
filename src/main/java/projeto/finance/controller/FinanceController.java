package projeto.finance.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import projeto.finance.dao.TransactionDao;

@Controller // Use @Controller for serving HTML views
@RequestMapping("/api/transactions") // Base URL for the controller
public class FinanceController {

    @Autowired
    private TransactionDao transactionDao;

    // Show Add Transaction Form
    @GetMapping("/add") 
    public String showAddForm() {
        return "addTransaction"; // Returns the addTransaction.html view
    }

    // Handle adding a transaction
    @PostMapping("/add")
    public String addTransaction(@RequestParam String description, @RequestParam double amount) {
        transactionDao.addTransaction(description, amount);
        return "redirect:/api/transactions/list"; // Redirect to the list of transactions
    }

    // List all transactions
    @GetMapping("/list")
    public String listTransactions(Model model) {
        List<Map<String, Object>> transactions = transactionDao.getAllTransactions();
        model.addAttribute("transactions", transactions);
        return "listTransactions"; // Returns the listTransactions.html view
    }

    // Show Update Transaction Form
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Map<String, Object> transaction = transactionDao.getTransactionById(id);
        model.addAttribute("transaction", transaction);
        return "updateTransactions"; // Returns the updateTransaction.html view
    }
    

    // Handle updating a transaction
    @PostMapping("/update/{id}")
    public String updateTransaction(@PathVariable Long id, @RequestParam String description, @RequestParam double amount) {
    transactionDao.updateTransaction(id, description, amount);
    return "redirect:/api/transactions/list"; // Redireciona para a lista de transações
}

    // Handle deleting a transaction
    @PostMapping("/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionDao.deleteTransaction(id);
        return "redirect:/api/transactions/list"; // Redirect to the list of transactions
    }
}