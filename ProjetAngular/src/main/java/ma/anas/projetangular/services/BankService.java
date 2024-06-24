package ma.anas.projetangular.services;

import ma.anas.projetangular.entities.BankAccount;
import ma.anas.projetangular.entities.CurrentAccount;
import ma.anas.projetangular.repositories.BankAccountRepository;
import ma.anas.projetangular.entities.SavingAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BankService {
    @Autowired
    private BankAccountRepository bankAccountRepository;
    public void consulter(){
        BankAccount bankAccount=
                bankAccountRepository.findById("472815bc-db0e-454e-9795-dafd8197884b").orElse(null);
        if(bankAccount!=null) {
            System.out.println("*************");
            System.out.println(bankAccount.getId());
            System.out.println(bankAccount.getBalance());
            System.out.println(bankAccount.getStatus());
            System.out.println(bankAccount.getCreateAt());
            System.out.println(bankAccount.getCustomer().getName());
            System.out.println(bankAccount.getClass().getSimpleName());
            if (bankAccount instanceof CurrentAccount)
                System.out.println("Over Draft => " + ((CurrentAccount) bankAccount).getOverDraft());
            else if (bankAccount instanceof SavingAccount)
                System.out.println("Rate => " + ((SavingAccount) bankAccount).getInterestRate());

            bankAccount.getAccountOperations().forEach(op -> {
                System.out.println(op.getType() + "\t" + op.getOperationDate() + "\t" + op.getAmount());
            });
        }
    }
}
