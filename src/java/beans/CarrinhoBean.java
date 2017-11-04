/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import model.Produto;

/**
 *
 * @author Jess
 */
@Named(value = "carrinhoBean")
@SessionScoped
public class CarrinhoBean implements Serializable {
    
    private List<Produto> itens = new ArrayList<>();
    private Double total;

    public CarrinhoBean() {
    }

    public List<Produto> getItens() {
        return itens;
    }

    public void setItens(List<Produto> itens) {
        this.itens = itens;
    }
    
    public String consultarCarrinho() {
        return "/protected/carrinho";
    }
    
    public String adicionarCarrinho(Produto produto) {
        boolean novo = true;
        for (Produto i : itens) {
            if (i.getId().equals(produto.getId())) {
                i.setQuantidade(i.getQuantidade() + 1);
                novo = false;
                break;
            }
        }
        if (novo) {
            produto.setQuantidade(1);
            itens.add(produto);
        }
        return null;
    }

    public String excluir(Produto produto) {
        itens.remove(produto);
        return null;
    }
    
    public Double getTotal() {
        Double tot = 0.0;
        for (Produto i : itens) {
            tot += i.getQuantidade() * i.getValor();
        }
        return tot;
    }
}
