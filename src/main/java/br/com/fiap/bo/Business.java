package br.com.fiap.bo;

import br.com.fiap.dao.HabitoDao;
import br.com.fiap.exceptions.CriacaoHabito;
import br.com.fiap.model.Habito;

public class Business {

    public void cadastraHabito(Habito h) throws CriacaoHabito{
        HabitoDao banco = new HabitoDao();
        if(h.isCompleto()){
            banco.insert(h);
        }
        else{
            throw new CriacaoHabito("O hábito não pode ser criado por possuir informações vazias. Tente novamente");
        }
    }
}
