package br.inatel.projetoFinal;

import br.inatel.projetoFinal.Cenarios.*;
import br.inatel.projetoFinal.Characters.*;
import br.inatel.projetoFinal.Database.AliancaDB;
import br.inatel.projetoFinal.Database.PlayableDB;
import br.inatel.projetoFinal.Database.WeaponDB;
import br.inatel.projetoFinal.Weapons.Arco;
import br.inatel.projetoFinal.Weapons.Espada;
import br.inatel.projetoFinal.Weapons.Weapon;
import br.inatel.projetoFinal.aliancas.Alianca;

import java.util.Objects;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Playable jogador = null;
        PlayableDB playabledb = new PlayableDB();
        WeaponDB weaponDB = new WeaponDB();
        AliancaDB aliancaDB = new AliancaDB();
        Alianca aliancaPrincipal = new Alianca(jogador, "Homem", 4);
        int decisions = 0;

        System.out.println("================================================================================================================================");
        System.out.println();
        System.out.println("A Batalha de Whiterun foi uma importante batalha que ocorreu durante a Guerra Civil de Skyrim. Aconteceu em Whiterun Hold.\n" +
                "Whiterun Hold tinha sido território neutro durante a Guerra Civil Skyrim, com escaramuças em ambos os lados ocorrendo no Hold e \n" +
                "no Fort Greymoor trocando de mãos entre o Império e os Stormcloaks em uma base regular. O Jarl Balgruuf recusou o direito da \n" +
                "Legião de guarnecer as tropas na cidade de Whiterun, por outro lado, ele também se recusou a reconhecer a reivindicação de Ulfric.");
        System.out.println();

        System.out.println("Primeiramente, vamos criar seu personagem: ");

        jogador = Cenarios.criarPersonagem();
        jogador.alianca=aliancaPrincipal;
        aliancaPrincipal.setRaca(jogador.getRaca());
        aliancaPrincipal.setLider(jogador);

        playabledb.insertPersonagem(jogador);
        for (Weapon w: jogador.getArmas()) {
            if(w != null){
                weaponDB.insertWeapon(w,jogador);
            }
        }
        aliancaDB.insertAlianca(aliancaPrincipal);


        decisions = Cenarios.cenarioPrincipal(jogador);


        switch (decisions) {
            case 0:
                decisions = Cenarios.cenarioAjudarEspada(jogador);
                break;
            case 1:
                decisions = Cenarios.cenarioAjudarArco(jogador);
                break;
            case 2:
                decisions = Cenarios.cenarioFicarEspada(jogador);
                break;
            case 3:
                decisions = Cenarios.cenarioFicarArco(jogador);
                break;
            default:
                break;
        }

        int numMembrosNovaAlianca;
        if(decisions == 0){
            numMembrosNovaAlianca = 3;
        }else{
            numMembrosNovaAlianca = 5;
            aliancaPrincipal.setNumMembros(3);
        }

        Playable Alethius = null;
        Alianca aliancaAlethius = null;

        Espada genericSword = new Espada(3,"S");
        Arco genericArrow = new Arco(3,"L");

        if (Objects.equals(jogador.getRaca(), "Homem")) {
            Alethius = new Playable("Bosmer", "Orc", 20);
            Alethius.addWeapon(genericArrow);
            aliancaAlethius = new Alianca(Alethius,"Orc",numMembrosNovaAlianca);
        }else{
            Alethius = new Playable("Bosmer", "Homem", 20);
            Alethius.addWeapon(genericSword);
            aliancaAlethius = new Alianca(Alethius,"Orc",numMembrosNovaAlianca);
        }
        aliancaPrincipal.adicionarAlianca(aliancaAlethius);
        aliancaAlethius.adicionarAlianca(aliancaPrincipal);

        playabledb.insertPersonagem(Alethius);
        for (Weapon w: Alethius.getArmas()) {
            if(w != null){
                weaponDB.insertWeapon(w,Alethius);
            }
        }
        aliancaDB.insertAlianca(aliancaAlethius);
        aliancaDB.updateAliados(aliancaPrincipal);
        aliancaDB.updateAlianca(aliancaPrincipal,aliancaPrincipal.getNumMembros());

        Cenarios.cenarioFinal(jogador);

        System.out.println("Eu costumava ser um aventureiro como você, então levei uma flecha no joelho");
    }

}


