package br.inatel.projetoFinal.Cenarios;

import br.inatel.projetoFinal.Characters.GenerateRandNPC;
import br.inatel.projetoFinal.Characters.Playable;
import br.inatel.projetoFinal.Database.AliancaDB;
import br.inatel.projetoFinal.Database.PlayableDB;
import br.inatel.projetoFinal.Database.WeaponDB;
import br.inatel.projetoFinal.Weapons.*;
import br.inatel.projetoFinal.aliancas.Alianca;

import java.util.Objects;
import java.util.Scanner;

public abstract class Cenarios {

    private static PlayableDB pdb = new PlayableDB();
    private static WeaponDB wdb = new WeaponDB();
    private static AliancaDB adb = new AliancaDB();
    private static Scanner sc = new Scanner(System.in);

    public static Playable criarPersonagem() {
        boolean flag = true;
        Playable jogador;
        int hp = 20;
        String raca = null;
        while (flag) {
            System.out.println("Selecione sua raça: ");
            System.out.println("1 - Homem");
            System.out.println("2 - Orc");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    raca = "Homem";
                    flag = false;
                    break;

                case 2:
                    raca = "Orc";
                    flag = false;
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;
            }
        }
        flag = true;
        System.out.println("De um nome ao seu " + raca + "!");
        String nome = sc.nextLine();
        jogador = new Playable(nome, raca, hp);
        while (flag) {
            System.out.println("Selecione sua arma inicial: ");
            System.out.println("1 - Espada");
            System.out.println("2 - Arco");

            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    Espada esp1 = new Espada(4,"S");
                    jogador.addWeapon(esp1);
                    flag = false;
                    break;

                case 2:
                    Arco arc1 = new Arco(4,"L");
                    jogador.addWeapon(arc1);
                    flag = false;
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;
            }

        }
        //sc.close();
        jogador.setPlayer(true);
        return jogador;
    }

    public static int cenarioPrincipal(Playable jogador){
        int decisions = 0;

        System.out.println("Tudo parece embaçado e pouco claro; você ouve gritos, metal batendo e murmúrios. Mas isso rapidamente se dissipa no nada.");
        System.out.println(".");
        System.out.println(".");
        System.out.println(".");
        System.out.println("De repente, você escuta alguém chamar seu nome");
        System.out.println("Desconhecido: "+jogador.getNome()+"...");
        System.out.println("Desconhecido: "+jogador.getNome()+"!!!");
        System.out.println("Desconhecido: "+jogador.getNome().toUpperCase()+" LEVANTA, A GENTE PRECISA DE VOCÊ AQUI!!");
        System.out.println("Alethius: Você levanta assustado, e percebe que está no meio de uma batalha.");
        System.out.println("Depois de ser abatido, nosso comandante nos deu tempo para nos recompor dizendo: \"Vamos, precisamos nos recompor, nosso comandante acabou de ser abatido.\n" +
                "Considere suas capacidades diminuídas. Mesmo assim, os feridos lutam para lutar. \n" +
                "Não prevejo sua força contínua por muito tempo. Entendo e aceito que seria difícil para mim conceder esse ponto, mas acredito que você deve nos liderar devido a dois fatos: primeiro, meu orgulho seria destruído se eu não o fizesse e, segundo, você é nosso único irmão.");
        System.out.println();
        System.out.println("Selecione sua resposta: ");
        System.out.println("1 - Finalmente você admitiu que sou melhor que você.");
        System.out.println("2 - Certo, me ajude a levantar.");
        decisions = sc.nextInt();
        if(decisions == 1) {
            System.out.println(jogador.getNome() + ": Hump! Finalmente você admitiu que sou melhor que você, esse momento vai entrar pra história!");
            System.out.println("Alethius: Isso não é hora de fazer piada. Levanta daí");
        }
        else{
            System.out.println(jogador.getNome() + ": Certo, me da uma mão aqui!");
            System.out.println("Alethius: O todo poderoso comandante não consegue se levantar sozinho? Vai, estende o braço ai");
            System.out.println(jogador.getNome() + ": Se eu não te conhecesse desde que nascemos, diria que está zombando da minha cara");
        }
        System.out.println("Alethius estende sua mão e te ajuda a levantar.");

        System.out.println(jogador.getNome() + ":Me atualiza do status da batalha.");
        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println("Alethius: Certo. Nosso pelotão foi derrotado,sobramos só nós 4. Precisamos dar um jeito de nos agrupar com alguém, não vamos durar muito tempo assim.\n" +
                    "A aliança com os ORCs já está enfraquecida, ninguém se ajuda, só estamos juntos para contar números.Eles não confiam em nós e, pra ser bem sincero,\n" +
                    "também não confio nesses dentudos, se a Legião Imperial vencer a batalha, Whiterun permanecerá como parte do Império, no entanto, se os Stormcloaks vencerem, Vignar Gray-Mane é nomeado Jarl.");
            System.out.println(jogador.getNome() + ": Nem todos os Orcs pensam assim.");
            System.out.println("Enquanto você falava, você percebe um grupo de orcs vindo na direção de seus homens.\n" +
                    "Também ao seu lado, você percebe um grupo de soldados feridos sendo cercados por quatro orcs. O líder te vê e clama por ajuda.");

            System.out.println("Selecione sua ação: ");
            System.out.println("1 - Manter posição e ajudar seu pelotão");
            System.out.println("2 - Tentar ajudar os Orcs");
            decisions = sc.nextInt();
            sc.nextLine();
            if(decisions == 1) {
                System.out.println(jogador.getNome() + ": Homens, de pé, nós defendemos nosso posto!");
                if(jogador.getArmaType(0) == 1){//Se arma inicial for espada
                    sc.close();
                    return 0;
                }else{
                    sc.close();
                    return 1;
                }
            }
            else{
                System.out.println(jogador.getNome() + ": Segura as pontas um segundo, eu já volto!");
                if(jogador.getArmaType(0) == 1){//Se arma inicial for espada
                    sc.close();
                    return 2;
                }else{
                    sc.close();
                    return 3;
                }
            }


        }else{
            System.out.println("Alethius: Certo. Nosso pelotão foi derrotado,sobramos só nós 4. Precisamos dar um jeito de nos agrupar com alguém, não vamos durar muito tempo assim.\n" +
                    "A aliança com os homens já está enfraquecida, ninguém se ajuda, só estamos juntos para contar números. Eles não confiam em nós e, pra ser bem sincero,\n" +
                    "também não confio nesses mortais imundos, se Alethius tomar Whiterun, eles vão se aliar a ele e se corromper ao poder do Império, assim como seus antigos reis");
            System.out.println(jogador.getNome() + ": Nem todos os homens pensam assim.");
            System.out.println("Narrador: Enquanto você falava, você percebe um grupo de orcs vindo na direção do seu pelotão.\n" +
                    "Também ao seu lado, você percebe um grupo de homens feridos sendo cercados por quatro orcs. O líder te vê e clama por ajuda.");

            System.out.println("Selecione sua ação: ");
            System.out.println("1 - Manter posição e ajudar seu pelotão");
            System.out.println("2 - Tentar ajudar os homens");
            decisions = sc.nextInt();
            sc.nextLine();
            if(decisions == 1) {
                System.out.println(jogador.getNome() + ": Guerreiros, de pé, nós defendemos nosso posto!");
                if(jogador.getArmaType(0) == 1){//Se arma inicial for espada

                    return 0;
                }else{

                    return 1;
                }
            }
            else{
                System.out.println(jogador.getNome() + ": Segura as pontas um segundo, eu já volto!");
                if(jogador.getArmaType(0) == 1){//Se arma inicial for espada

                    return 2;
                }else{
;
                    return 3;
                }
            }
        }
    }

    public static int cenarioFicarEspada(Playable jogador){
        int decisions = 0;
        System.out.println("Narrador: Você e seus aliados se preparam para a batalha contra 4 orcs.\n" +
                "Durante a batalha, você comando seus aliados para derrotar os orcs, até que um deles vem em sua direção\n" +
                "Você saca sua espada e se prepara para batalha.");
        System.out.println();
        System.out.println();
        Playable orc = GenerateRandNPC.generateRandNPC("Orc");
        pdb.insertPersonagem(orc);
        System.out.println("Dados do jogador:");
        System.out.println("HP: " + jogador.getHP());
        System.out.println("Arma: Espada");
        System.out.println("Dano da arma: " + jogador.getArma(1).getDano());
        System.out.println("Dados do Inimigo:");
        System.out.println("HP: " + orc.getHP());
        System.out.println("Arma: Espada e porradaria franca");
        System.out.println("Dano da arma: " + orc.getArma(1).getDano());
        System.out.println("Narrador: O orc vem pra cima de você ferozmente ");
        System.out.println("Selecione sua ação: ");
        System.out.println("1 - Esquivar");
        System.out.println("2 - Bloquear o ataque");
        decisions = sc.nextInt();
        sc.nextLine();
        if(decisions == 1) {
            System.out.println("Narrador: Você esquiva do ataque, mas o peso da espada te deixa lento, e o orc acaba atingindo seu ombro\n." +
                    "Mesmo assim, o orc se desequilibra e cai e você consegue atingi-lo, desprevenido, em um ponto fraco, causando um dano critico");
            orc.atacar(jogador,1,0);
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
            pdb.updateHP(jogador);
        }
        else{
            System.out.println("Narrador: Você bloqueia o ataque e o orc se desequilibra e cai. Com toda sua força, você crava a espada no crânio do orc, matando-ô");
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
        }

        System.out.println("Alethius: Você está bem? Não consegui vir te ajudar a tempo, mas deu pra ver que você deu conta do recado.");

        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de orcs em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um orcs" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Alethius : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Adrianne Avenicci : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Alethius percebem que foram cercados por um exército de incontáveis orcs");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um Orc");
            System.out.println("Alethius : Você devia se sentir honrado.");
            System.out.println("Adrianne Avenicci : Que? Esse maluco de orelha pontuda xingou a gente?");
            System.out.println(jogador.getNome() + ": Não, ele disse 'Que você tenha paz na morte'.");
            System.out.println("Alethius : Primeiro, onde você aprendeu a falar Quenya? Segundo, que astral baixo pra quem tem que encorajar um grupo de soldados a lutar contra" +
                    "pelo menos 200 orcs");
            System.out.println("Adrianne Avenicci : Foco na batalha!");
        }
        else{
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de homens em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um homem" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Alethius  : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Adrianne Avenicci  : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Alethius percebem que foram cercados por um exército de incontáveis orcs.");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um Orc");
            System.out.println("Alethius : Você devia se sentir honrado.");
            System.out.println("Adrianne Avenicci  : Honrado em morrer ao lado de uma raça corrompida por poder e ganância. Não me parece muito coerente");
            System.out.println("Alethius : Ai o orelha pontuda, se você quiser morrer mais cedo é só falar, to doidinho pra espetar um otário");
            System.out.println(jogador.getNome() + ": Foco na batalha!");
        }

        return 0;
    }

    public static int cenarioFicarArco(Playable jogador){
        int decisions = 0;
        System.out.println("Narrador: Você e seus aliados se preparam para a batalha contra 4 orcs.\n" +
                "Durante a batalha, você comando seus aliados para derrotar os orcs, até que um deles vem em sua direção\n" +
                "Você saca seu arco e se prepara para batalha.");
        System.out.println();
        System.out.println();
        Playable orc = GenerateRandNPC.generateRandNPC("Orc");
        pdb.insertPersonagem(orc);
        System.out.println("Dados do jogador:");
        System.out.println("HP: " + jogador.getHP());
        System.out.println("Arma: Arco");
        System.out.println("Dano da arma: " + jogador.getArma(0).getDano());
        System.out.println("Dados do Inimigo:");
        System.out.println("HP: " + orc.getHP());
        System.out.println("Arma: Espada e porradaria franca");
        System.out.println("Dano da arma: " + orc.getArma(1).getDano());
        System.out.println("Narrador: O orc vem pra cima de você ferozmente ");
        System.out.println("Selecione sua ação: ");
        System.out.println("1 - Esquivar");
        System.out.println("2 - Bloquear o ataque");
        decisions = sc.nextInt();
        sc.nextLine();
        if(decisions == 1) {
            System.out.println("Narrador: Você esquiva do ataque com maestria e agilidade e o orc acaba atingindo o solo\n." +
                    "Aproveitando o momento, você dispara uma flecha na cabeça do orc desprevenido, matando-ô");
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
        }
        else{
            System.out.println("Narrador: Você bloqueia o ataque com seu arco com o braço de sua armadura, mas a força do impacto é tão grande que você sente seu ombro deslocar.\n" +
                    " Mesmo assim, você rapidamente chuta o orc, que se desequilibra e cai. Com o braço que lhe resta, você crava uma flecha no crânio do orc, matando-ô. Após mata-lô, você coloca seu ombro no lugar" +
                    "e se prepara para mais uma batalha");
            orc.atacar(jogador,1,0);
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.updateHP(jogador);
            pdb.deletePlayable(orc.getNome());
        }

        System.out.println("Adrianne Avenicci: Você está bem? Não consegui vir te ajudar a tempo, mas deu pra ver que você deu conta do recado.");

        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de Orcs em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um orc" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Alethius : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Adrianne Avenicci : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Alethius percebem que foram cercados por um exército de incontáveis orcs.");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um orc");
            System.out.println("Alethius : Você devia se sentir honrado. Nai yaryuvalyë estë sambassë Mandosto");
            System.out.println("Adrianne Avenicci : Que? Esse maluco de orelha pontuda xingou a gente?");
            System.out.println(jogador.getNome() + ": Não, ele disse 'Que você tenha paz na morte'.");
            System.out.println("Adrianne Avenicci : Primeiro, onde você aprendeu a falar Quenya? Segundo, que astral baixo pra quem tem que encorajar um grupo de soldados a lutar contra" +
                    "pelo menos 200 orcs");
            System.out.println("Alethius : Foco na batalha!");
        }
        else{
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de homens em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um homem" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Alethius : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Adrianne Avenicci : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Alethius percebem que foram cercados por um exército de incontáveis orcs.");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um orc");
            System.out.println("Alethius : Você devia se sentir honrado.");
            System.out.println("Adrianne Avenicci : Honrado em morrer ao lado de uma raça corrompida por poder e ganância. Não me parece muito coerente");
            System.out.println("Alethius : Ai o orelha pontuda, se você quiser morrer mais cedo é só falar, to doidinho pra espetar um otário");
            System.out.println(jogador.getNome() + ": Foco na batalha!");
        }

        return 0;
    }

    public static int cenarioAjudarEspada(Playable jogador){
        int decisions = 0;
        System.out.println("Narrador: Você deixa seus aliados para ajudar o grupo que está sendo cercado por 4 orcs.\n" +
                "Durante a batalha, um dos orcs vem em sua direção\n" +
                "Você saca ua espada e se prepara para batalha.");
        System.out.println();
        System.out.println();
        Playable orc = GenerateRandNPC.generateRandNPC("Orc");
        pdb.insertPersonagem(orc);
        System.out.println("Dados do jogador:");
        System.out.println("HP: " + jogador.getHP());
        System.out.println("Arma: Espada");
        System.out.println("Dano da arma: " + jogador.getArma(0).getDano());
        System.out.println("Dados do Inimigo:");
        System.out.println("HP: " + orc.getHP());
        System.out.println("Arma: Espada e porradaria franca");
        System.out.println("Dano da arma: " + orc.getArma(1).getDano());
        System.out.println("Narrador: O orc vem pra cima de você ferozmente ");
        System.out.println("Selecione sua ação: ");
        System.out.println("1 - Esquivar");
        System.out.println("2 - Bloquear o ataque");
        decisions = sc.nextInt();
        sc.nextLine();
        if(decisions == 1) {
            System.out.println("Narrador: Você esquiva do ataque, mas o peso da espada te deixa lento, e o orc acaba atingindo seu ombro\n." +
                    "Mesmo assim, o orc se desequilibra e cai e você consegue atingi-lo, desprevenido, em um ponto fraco, causando um dano critico");
            orc.atacar(jogador,1,0);
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
            pdb.updateHP(jogador);
        }
        else{
            System.out.println("Narrador: Você bloqueia o ataque e o orc se desequilibra e cai. Com toda sua força, você crava a espada no crânio do orc, matando-ô");
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
        }

        System.out.println("Alethius: Você está bem? Não consegui vir te ajudar a tempo, mas deu pra ver que você deu conta do recado.");

        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, meus homens precisam de ajuda");
            System.out.println("Narrador: Vocês correm em direção ao seu grupo, mas não chega a tempo de salvar todos. Ao chegarem, Adrianne Avenicci" +
                    "vem e sua direção");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Alethius : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Adrianne Avenicci : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Alethius percebem que foram cercados por um exército de incontáveis orcs.");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um orc");
            System.out.println("Alethius : Você devia se sentir honrado. Nai yaryuvalyë estë sambassë Mandosto");
            System.out.println("Adrianne Avenicci : Que? Esse maluco de orelha pontuda xingou a gente?");
            System.out.println(jogador.getNome() + ": Não, ele disse 'Que você tenha paz na morte'.");
            System.out.println("Adrianne Avenicci : Primeiro, onde você aprendeu a falar Quenya? Segundo, que astral baixo pra quem tem que encorajar um grupo de soldados a lutar contra" +
                    "pelo menos 200 orcs");
            System.out.println("Alethius : Foco na batalha!");
        }
        else{
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de homens em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um homem" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Alethius : Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Adrianne Avenicci : Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Alethius percebem que foram cercados por um exército de incontáveis orcs.");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um orc");
            System.out.println("Alethius : Você devia se sentir honrado.");
            System.out.println("Adrianne Avenicci : Honrado em morrer ao lado de uma raça corrompida por poder e ganância. Não me parece muito coerente");
            System.out.println("Alethius : Ai o orelha pontuda, se você quiser morrer mais cedo é só falar, to doidinho pra espetar um otário");
            System.out.println(jogador.getNome() + ": Foco na batalha!");
        }

        return 1;
    }

    public static int cenarioAjudarArco(Playable jogador){
        int decisions = 0;
        System.out.println("Narrador: Você deixa seus aliados para ajudar o grupo que está sendo cercado por 4 orcs.\n" +
                "Durante a batalha, um dos orcs vem em sua direção\n" +
                "Você saca ua espada e se prepara para batalha.");
        System.out.println();
        System.out.println();
        Playable orc = GenerateRandNPC.generateRandNPC("Orc");
        pdb.insertPersonagem(orc);
        System.out.println("Dados do jogador:");
        System.out.println("HP: " + jogador.getHP());
        System.out.println("Arma: Espada");
        System.out.println("Dano da arma: " + jogador.getArma(0).getDano());
        System.out.println("Dados do Inimigo:");
        System.out.println("HP: " + orc.getHP());
        System.out.println("Arma: Espada e porradaria franca");
        System.out.println("Dano da arma: " + orc.getArma(1).getDano());
        System.out.println("Narrador: O orc vem pra cima de você ferozmente ");
        System.out.println("Selecione sua ação: ");
        System.out.println("1 - Esquivar");
        System.out.println("2 - Bloquear o ataque");
        decisions = sc.nextInt();
        sc.nextLine();
        if(decisions == 1) {
            System.out.println("Narrador: Você esquiva do ataque com maestria e agilidade e o orc acaba atingindo o solo\n." +
                    "Aproveitando o momento, você dispara uma flecha na cabeça do orc desprevenido, matando-ô");
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.deletePlayable(orc.getNome());
        }
        else{
            System.out.println("Narrador: Você bloqueia o ataque com seu arco com o braço de sua armadura, mas a força do impacto é tão grande que você sente seu ombro deslocar.\n" +
                    " Mesmo assim, você rapidamente chuta o orc, que se desequilibra e cai. Com o braço que lhe resta, você crava uma flecha no crânio do orc, matando-ô. Após mata-lô, você coloca seu ombro no lugar" +
                    "e se prepara para mais uma batalha");
            orc.atacar(jogador,1,0);
            jogador.atacar(orc,1,1);
            System.out.println("Resultados da batalha: ");
            System.out.println("HP restante: " + jogador.getHP());
            pdb.updateHP(orc);
            pdb.updateHP(jogador);
            pdb.deletePlayable(orc.getNome());
        }

        System.out.println("Alethius: Você está bem? Não consegui vir te ajudar a tempo, mas deu pra ver que você deu conta do recado.");

        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, meus homens precisam de ajuda");
            System.out.println("Narrador: Vocês correm em direção ao seu grupo, mas não chega a tempo de salvar todos. Ao chegarem, Adrianne Avenicci" +
                    "vem e sua direção");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Alethius: Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Adrianne Avenicci: Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Alethius percebem que foram cercados por um exército de incontáveis orcs.");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um orc");
            System.out.println("Alethius: Você devia se sentir honrado");
            System.out.println("Adrianne Avenicci: Que? Esse maluco de orelha pontuda xingou a gente?");
            System.out.println(jogador.getNome() + ": Não, ele disse 'Que você tenha paz na morte'.");
            System.out.println("Adrianne Avenicci: Primeiro, onde você aprendeu a falar Quenya? Segundo, que astral baixo pra quem tem que encorajar um grupo de soldados a lutar contra" +
                    "pelo menos 200 orcs");
            System.out.println("Alethius: Foco na batalha!");
        }
        else{
            System.out.println(jogador.getNome() + ": Depois nos preocupamos com isso, tem um grupo de homens em perigo logo ao lado");
            System.out.println("Narrador: Vocês correm em direção ao grupo, mas não chegam a tempo de salvar todos. Ao chegarem, um homem" +
                    "vem e sua direção e pede para falar com o líder");
            System.out.println(jogador.getNome() + ": Perdão, não conseguimos chegar a tempo");
            System.out.println("Alethius: Não é hora de se preocupar com isso, estamos em meio a uma guerra. Tivemos sorte de ter poucas baixas.");
            System.out.println("Adrianne Avenicci: Desculpa interromper vossa senhoria(com ironia), mas eu acho que vale a pena vocês darem uma olhada em volta");
            System.out.println("Narrador: Ao olhar, você e Alethius percebem que foram cercados por um exército de incontáveis orcs.");
            System.out.println(jogador.getNome() + ": É, parece que vou morrer lutando ao lado de um orc");
            System.out.println("Alethius: Você devia se sentir honrado.");
            System.out.println("Adrianne Avenicci: Honrado em morrer ao lado de uma raça corrompida por poder e ganância. Não me parece muito coerente");
            System.out.println("Alethius: Ai o orelha pontuda, se você quiser morrer mais cedo é só falar, to doidinho pra espetar um otário");
            System.out.println(jogador.getNome() + ": Foco na batalha!");
        }

        return 1;
    }

    public static int cenarioFinal(Playable jogador){
        int decisions = 0;
        System.out.println("Narrador: Você e Alethius comandam seus aliados em um batalha feroz contra os orcs. Mas eles são muitos. Outros homens e orcs que estão por perto se juntam,\n" +
                "mas mesmo assim não é o suficiente. Um a um você ve seus aliados morrendo.");
        System.out.println("Distraido pela cena, você é atingido por um orc e derruba sua arma. Ao olhar para o lado, você percebe que Adrianne Avenicci está no chão, com uma espada cravada no peito.\n");
        System.out.println("Isso te enche de fúria, e você pega uma espada no chão e começa a brandir ela com tal força que todos os orcs perto se assutam e recuam. Ao chegar até Adrianne Avenicci,\n" +
                "ele te olha nos olhos e diz com seus últimos suspiros:");
        System.out.println("Adrianne Avenicci: Parece que você realmente é mais forte do que eu afinal de contas... Não deixe minha morte ser em vão irmão, honre minha vida e minha morte. Morra, mas morra lutando.");
        System.out.println("Narrador: Adrianne Avenicci fecha os olhos e para de respirar. Em prantos, você se levanta, disposto a cumprir sua promessa. Você olha ao lado e vê homens e orcs amedrontados,\n" +
                "desesperados com a batalha, com medo da morte. Com o resto da força que lhe resta, você pega uma espada, sobe em uma pedra, ergue o braço e grita:");
        if(Objects.equals(jogador.getRaca(), "Homem")) {
            System.out.println(jogador.getNome() + ": Levantem-se, levantem-se, Guerreiros!! Lanças serão brandidas, escudos serão quebrados. Um dia de luta,de espadas, um dia vermelho!\n" +
                    "Antes que o sol nasça, lutem! Marchem em direção a morte! Pela honra de nossas terras e amigos! PELA MORTE!");
            System.out.println("Multidão: MORTE!!!! MORTEEE!!!!!!");
            System.out.println(jogador.getNome() + ": PELA MORTEEEE!");
        }else{
            System.out.println(jogador.getNome() + ": Levantem-se, levantem-se, Guerreiros!!! Lanças serão brandidas, escudos serão quebrados. Um dia de luta,de arcos e flechas, um dia vermelho!\n" +
                    "Antes que o sol nasça, lutem! Marchem em direção a morte! Pela honra de nossas terras e amigos! PELA MORTE!");
            System.out.println("Multidão: MORTE!!!! MORTEEE!!!!!!");
            System.out.println(jogador.getNome() + ": PELA MORTEEEE!");
        }
        System.out.println("Narrador: Você e Alethius comandam seus aliados em uma última batalha, em direção a morte. A fúria e inspiração nos corações dos guerreiros lhe dão uma força que\n" +
                "assusta os orcs. Homens e orcs perfurados por espadas e flechas, com braços dilacerados e pernas quebras, usam suas últimas forças para dizimar a maior quantidade de orcs.");
        System.out.println("Narrador: Mas mesmo assim, os orcs eram muitos. Pouco a pouco todos foram morrendo. Mas morriam com orgulho e honra, felizes pela luta. O cansaço te atinge e você cai ao solo.\n" +
                "Aceitando sua morte, você olha em volta. Alethius também está no seu final, e os homens e orcs que restaram foram poucos.");
        System.out.println("Narrador: Quando você enfim aceitará a sua morte, você vê um clarão na distância e um grito de dor. Alethius havia cortado inúmeras cabeças de Orcs.\n" +
                "Este mantinha a essência de Whiterun no corpo físico, havia sido cortado. Logo em seguida, o clarão se aumenta e o corpo físico do Senhor das armas se desintegra em uma explosão\n" +
                "de luz e força, derrubando todos e fazendo com que todos os orcs recuassem com medo.");
        System.out.println("Narrador: O cerco de Imperiais chegará ao fim, e A Última Aliança saiu vitoriosa.");
        System.out.println("Narrador: Você se levanta e olha para Alethius. Ele se despede, sinalizando assim o fim da aliança entre Homens e orcs");
        System.out.println("Narrador: Após essa batalha, os orcs se retiraram e navegaram em direção as montanhas, a Terra dos Orcs, para viver em meio as névoas.\n" +
                "Já os homens, continuaram a construir seu império.");
        sc.close();
        return 0;
    }

}
