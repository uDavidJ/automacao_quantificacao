package com.example.automacao_quantificacao;

public class Calculos {

    public static int campo_pts_pavimentos;
    public static int campo_n_pavimentos;
    public static int campo_dt_malha;
    public static String categoria;
    public static String campo_voz;
    public static String campo_seg;
    public static String campo_dados;
    public static boolean bandeja_deslizante = false;
    public static boolean DVR = false;
    public static boolean area_reservada = false;


    public static int tomadas = 0;
    public static int seg = 0;
    public static int voz = 0;
    public static int  dados = 0;
    public static int  pav = 0;
    public static int  malha_horizontal = 0;
    public static int  PP = 0;
    public static int  OF = 0;
    public static String categoria_cabo = " ";
    public static String modelo_rack = " ";

    public static int tamanho_dios = 48;
    public static int tamanho_tos = 8;
    public static boolean todio = false;
    public static int qtd_acop = 0;
    public static int nro_dios = 0;
    public static int tam1 = 0;
    public static int tam2 = 0;
    public static int cabos =0;
    public static int n_switches = 0;

    public static double tamanho_rack_set = 0;
    public static double tamanho_rack_seq = 0;

    private static boolean regua_fechamento = false;
    private static String cat_fibra;
    private static int pe_direito;
    private static int backbones_andar;
    private static int fibras_andar;

    private static int qtd_pares_externo;
    private static int distancia_externo;

    public static void setParesExterno(int valor) {
        qtd_pares_externo = valor;
    }

    public static int getQtdParesExterno() {
        return qtd_pares_externo;
    }

    public static void setDistanciaExterna(int valor) {
        distancia_externo = valor;
    }

    public static int getDistanciaExterna() {
        return distancia_externo;
    }

    public static void setRegua(boolean valor) {
        regua_fechamento = valor;
    }

    public static void setFibra(String valor) {
        if (valor.equals("1GB"))
            cat_fibra = "50x125";
        else if (valor.equals("10GB") && (campo_n_pavimentos*pe_direito < 300))
            cat_fibra = "50x125";
        else
            cat_fibra = "9x125";
    }

    public static void setPeDireito(int valor) {
        pe_direito = valor;
    }

    public static void setBackbones(int valor) {
        backbones_andar = valor;
    }

    public static void setFibrasAndar(int valor) {
        fibras_andar = valor;
    }

    public static void setReguaFechamento(boolean valor) {
        regua_fechamento = valor;
    }


    public static void calcula() {


        if(ArmazenaEscolha.escolha.contains("mh") || ArmazenaEscolha.escolha.contains("horizontal")) {
            //Conversoes e variaveis
            tomadas = campo_pts_pavimentos*2;
            seg = Integer.parseInt(campo_seg);
            voz = Integer.parseInt(campo_voz);
            dados = Integer.parseInt(campo_dados);
            pav = campo_n_pavimentos;
            malha_horizontal = campo_dt_malha * tomadas;
            PP = (int) Math.ceil(tomadas/24.0);
            OF = 2*PP;
            categoria_cabo = categoria;
            modelo_rack = "";

            //Etiqueta de identificação de tomadas e espelhos
            Integer ETITE = campo_pts_pavimentos*3;


            double tamanho_rack = 0.0;
            int[] rack_informacoes;
            Integer tamanho_bandeja;

            if (bandeja_deslizante)
                tamanho_bandeja = 6;
            else
                tamanho_bandeja = 4;

            ObjetoColuna ob = new ObjetoColuna("ÁREA DE TRABALHO", " ", " ", " ");
            ArmazenaListaObjetosColuna.lista.add(ob);

            //Quantificacao das tomadas
            ob = new ObjetoColuna("Tomada RJ-45 fêmea (" + categoria_cabo + ")", "Unid.", tomadas, pav*tomadas);
            ArmazenaListaObjetosColuna.lista.add(ob);

            //Quantificacao dos espelhos
            ob = new ObjetoColuna("Espelho de conexão 2x4", "Unid.", tomadas/2, (tomadas/2)*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);

            //Quantificacao dos patch cords
            ob = new ObjetoColuna("Cordão de ligação (Patch cord), (Cor: Azul) (Tamanho: 3M) (" + categoria_cabo + ")", "Unid.", tomadas, tomadas*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);

            ob = new ObjetoColuna(" ", " ", " ", " ");
            ArmazenaListaObjetosColuna.lista.add(ob);

            ob = new ObjetoColuna("MALHA HORIZONTAL", " ", " ", " ");
            ArmazenaListaObjetosColuna.lista.add(ob);

            //Quantificacao cabo UTP rigido
            malha_horizontal = (int) Math.ceil((double) malha_horizontal/305);
            ob = new ObjetoColuna("Cabo UTP rígido para malha horizontal (" + categoria_cabo + ")", "Cxs", malha_horizontal, malha_horizontal*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);

            if (DVR)
                tamanho_rack += 2;


            tamanho_rack += tamanho_rack + ((double) ((PP*2) + OF + tamanho_bandeja));
            tamanho_rack = tamanho_rack*1.5;
            rack_informacoes = calculaTamanhoRack(tamanho_rack);

            if(ArmazenaEscolha.escolha.contains("backbone"))
                tamanho_rack_set+=tamanho_rack;
            else
                tamanho_rack_set+=rack_informacoes[0];


//            ob = new ObjetoColuna("Rack (" + modelo_rack + ")" + "(Tamanho: " + String.valueOf(rack_informacoes[0]) + "U)", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna(" ", " ", " ", " ");
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("MISCELÂNEA - MALHA HORIZONTAL", " ", " ", " ");
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            //Miscelanea
//            ob = new ObjetoColuna("Etiquetas para tomadas e espelhos", "Unid.", ETITE, ETITE*pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Etiqueta para identificação do cabo de malha horizontal", "Unid.", tomadas*2, (tomadas*2) * pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Etiquetas para identificação de portas do Patch Panel", "Unid.", PP*24, (PP*24) * pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Etiquetas para Rack", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Etiquetas para SW e PP", "Unid.", PP*2, (PP*2) * pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Etiquetas para identificação dos Patch Cables", "Unid.", tomadas*2, (tomadas*2) * pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Abraçadeira de velcro", "m", rack_informacoes[1]*3, rack_informacoes[1]*3*pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Abraçadeira Hellermann (Pacote com 100 unidades)", "Conj.", rack_informacoes[1], rack_informacoes[1]*pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Filtro de linha 6 tomadas", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);
//
//            ob = new ObjetoColuna("Parafuso Porca Gaiola (Conjunto com 10 unidades)", "Conj.", (int) Math.ceil(rack_informacoes[0]*4*rack_informacoes[1]/10.0), (int) Math.ceil(rack_informacoes[0]*4*rack_informacoes[1]/10.0)*pav);
//            ArmazenaListaObjetosColuna.lista.add(ob);

        }
    }


    public static void calculafibra() {
        final int[] tamanhos_dio = {12, 16, 24, 48};
        final int[] tamanhos_to = {2, 4, 6, 8};
        tamanho_dios = 48;
        tamanho_tos = 8;
        todio = false;

        int nro_etiquetas = 0;

        for (int i = 0; i < 4; i++) {
            if (fibras_andar <= tamanhos_dio[i]) {
                tamanho_dios = tamanhos_dio[i];
                break;
            }
        }

        if (fibras_andar > 8) {
            todio = true;
            for (int i = 0; i < 4; i++) {
                if (fibras_andar/2 <= tamanhos_dio[i]) {
                    tamanho_tos = tamanhos_dio[i];
                    break;
                }
            }
        } else {
            for (int i = 0; i < 4; i++) {
                if (fibras_andar <= tamanhos_to[i]) {
                    tamanho_tos = tamanhos_to[i];
                    break;
                }
            }
        }

        nro_dios = (int) Math.ceil(fibras_andar*(campo_n_pavimentos-1)/(tamanho_dios * 1.0));
        ObjetoColuna ob;

        qtd_acop = (fibras_andar * (campo_n_pavimentos-1))/2;


        if(todio)
            tamanho_rack_set+=2;
        nro_etiquetas += 2*qtd_acop;

        ob = new ObjetoColuna(" ", " ", " ", " ");
        ArmazenaListaObjetosColuna.lista.add(ob);

        tam1 = 3 * pe_direito;
        tam2 = 3 * pe_direito + pe_direito * (campo_n_pavimentos-2);
        cabos = (int) Math.ceil((tam1 + tam2) * (campo_n_pavimentos-1)/2.0 * 1.1);
        n_switches = (int) Math.ceil(fibras_andar * (campo_n_pavimentos-1)/24.0);

        int tam_rack = 2*n_switches + nro_dios;
        int[] rack = calculaTamanhoRack(tam_rack*1.5);
        tamanho_rack_seq+=rack[0];
        ob = new ObjetoColuna("CABEAMENTO ÓPTICO DE BACKBONE INTERNO ", "", "", "");
        ArmazenaListaObjetosColuna.lista.add(ob);
        ob = new ObjetoColuna("Cabo óptico "+fibras_andar+" fibras "+cat_fibra+" tight buffer", "m", cabos, cabos);
        ArmazenaListaObjetosColuna.lista.add(ob);


//        ob = new ObjetoColuna("Rack "+tipo_rack+" "+rack[0]+"U", "Unid.", rack[1], rack[1]);
//        ArmazenaListaObjetosColuna.lista.add(ob);
//
//        int porca_gaiola = (int) Math.ceil(rack[0] * 4/10.0);
//
//        ob = new ObjetoColuna("", " ", " ", " ");
//        ArmazenaListaObjetosColuna.lista.add(ob);
//
//        ob = new ObjetoColuna("MISCELÂNEA - BACKBONE", " ", " ", " ");
//        ArmazenaListaObjetosColuna.lista.add(ob);
//
//        ob = new ObjetoColuna("Parafuso Porca Gaiola (Conjunto com 10 unidades)", "Conj.", porca_gaiola, porca_gaiola);
//        ArmazenaListaObjetosColuna.lista.add(ob);
//
//        ob = new ObjetoColuna("Abraçadeira de velcro", "m", rack[1]*3, rack[1]*3);
//        ArmazenaListaObjetosColuna.lista.add(ob);
//
//        ob = new ObjetoColuna("Abraçadeira Hellermann", "Conj.", rack[1], rack[1]*3);
//        ArmazenaListaObjetosColuna.lista.add(ob);
//
//        ob = new ObjetoColuna("Filtro de linha 6 tomadas", "Unid.", rack[1], rack[1]);
//        ArmazenaListaObjetosColuna.lista.add(ob);
//
//        ob = new ObjetoColuna("Etiquetas para Rack", "Unid.", rack[1], rack[1]);
//        ArmazenaListaObjetosColuna.lista.add(ob);
//
//        ob = new ObjetoColuna("Etiquetas para pigtail e cordão óptico", "Unid.", nro_etiquetas, nro_etiquetas);
//        ArmazenaListaObjetosColuna.lista.add(ob);
    }


    public static int[] calculaTamanhoRack(double tamanho_rack) {


        double[] tamanhos_rack = {4.0, 6.0, 8.0, 10.0, 12.0, 16.0, 20.0, 24.0, 28.0, 32.0, 36.0, 40.0, 44.0};
        int quantidade_rack = 1;
        int[] vetor = new int[2];
        if(tamanho_rack==0.0) {
            vetor[0]=4;
            vetor[1]=1;
            return vetor;
        }
        while(true) {
            for(int i = 0; i<tamanhos_rack.length; i++) {
                if(tamanho_rack==tamanhos_rack[i]) {
                    tamanho_rack=tamanhos_rack[i];
                    vetor[0] = (int) tamanho_rack;
                    vetor[1] = quantidade_rack;
                    return vetor;
                }else if (i != 0 && i != (tamanhos_rack.length-1)) {
                    if(tamanho_rack > tamanhos_rack[i-1] && tamanho_rack < tamanhos_rack[i+1]) {
                        tamanho_rack = tamanhos_rack[i+1];
                        vetor[0] = (int) tamanho_rack;
                        vetor[1] = quantidade_rack;
                        return vetor;
                    }
                }
            }
            tamanho_rack/=2;
            quantidade_rack++;
        }
    }

    public static void calcula_rack_set(double tamanho_rack_set) {
        int[] rack_informacoes = Calculos.calculaTamanhoRack(tamanho_rack_set);

        ObjetoColuna ob;

        ob = new ObjetoColuna(" ", " ", " ", " ");
        ArmazenaListaObjetosColuna.lista.add(ob);

        if(!(ArmazenaEscolha.escolha.contains("backbone"))) {
            ob = new ObjetoColuna("RACK SET/SEQ", " ", " ", " ");
            ArmazenaListaObjetosColuna.lista.add(ob);
        }else {
            ob = new ObjetoColuna("RACK SET", " ", " ", " ");
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(ArmazenaEscolha.escolha.contains("backbone")){
            if (todio) {
                ob = new ObjetoColuna("Distribuidor Óptico (DIO) 1U "+tamanho_tos+" portas", "Unid.", 1, campo_n_pavimentos-1);
                ArmazenaListaObjetosColuna.lista.add(ob);

                ob = new ObjetoColuna("Switches (Altura: 1U)", "Unid.", PP+1, (PP+1)*campo_n_pavimentos);
                ArmazenaListaObjetosColuna.lista.add(ob);

                OF+=2;// um DIO e um SWITCH

                ob = new ObjetoColuna("Caixa de emenda", "Unid.", 2, (campo_n_pavimentos-1)*2);
                ArmazenaListaObjetosColuna.lista.add(ob);

                ob = new ObjetoColuna("Pigtail "+cat_fibra+" (Duplo) (Conector LC)", "Unid.", fibras_andar/2, (fibras_andar/2)*(campo_n_pavimentos-1));
                ArmazenaListaObjetosColuna.lista.add(ob);

                ob = new ObjetoColuna("Acoplador óptico (Duplo) (Conector LC)", "Unid.", fibras_andar/2, (fibras_andar/2)*(campo_n_pavimentos-1));
                ArmazenaListaObjetosColuna.lista.add(ob);

                ob = new ObjetoColuna("Cordão óptico "+cat_fibra+" (Duplo) (Conector LC) (2M)", "Unid.", fibras_andar/2, (fibras_andar/2)*(campo_n_pavimentos-1));
                ArmazenaListaObjetosColuna.lista.add(ob);

                //nro_etiquetas += 2 * ((fibras_andar/2)*(campo_n_pavimentos-1));
            } else {
                ob = new ObjetoColuna("Terminador Óptico (TO) "+tamanho_tos+" portas", "Unid.", 1, campo_n_pavimentos-1);
                ArmazenaListaObjetosColuna.lista.add(ob);

                ob = new ObjetoColuna("Pigtail "+cat_fibra+" (Simples) (Conector LC)", "Unid.", fibras_andar, fibras_andar*(campo_n_pavimentos-1));
                ArmazenaListaObjetosColuna.lista.add(ob);

                //nro_etiquetas += fibras_andar*(campo_n_pavimentos-1);
            }

        }else if(ArmazenaEscolha.escolha.contains("malha")){
            ob = new ObjetoColuna("Switches (Altura: 1U)", "Unid.", PP, PP*campo_n_pavimentos);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(ArmazenaEscolha.escolha.contains("malha")) {
            if(voz != 0) {
                ob = new ObjetoColuna("Patch Cable (Cor: Amarelo) (Tamanho: 2M) (" + categoria_cabo + ")", "Unid.", voz*2, voz * 2*campo_n_pavimentos);
                ArmazenaListaObjetosColuna.lista.add(ob);
            }

            if(seg != 0) {
                ob = new ObjetoColuna("Patch Cable (Cor: Vermelho) (Tamanho: 2M) (" +categoria_cabo + ")", "Unid.", seg*2, seg * 2*campo_n_pavimentos);
                ArmazenaListaObjetosColuna.lista.add(ob);
            }

            if(dados != 0) {
                ob = new ObjetoColuna("Patch Cable (Cor: Azul) (Tamanho: 2M) ("+categoria_cabo+")", "Unid.", dados*2, dados*2*campo_n_pavimentos);
                ArmazenaListaObjetosColuna.lista.add(ob);
            }

            //Patch Panel
            ob = new ObjetoColuna("Painel de telecomunicações 24 portas (Altura: 1U) (" + categoria_cabo + ")", "Unid.", PP, PP*campo_n_pavimentos);
            ArmazenaListaObjetosColuna.lista.add(ob);

        }
        if(PP != 0) {
            ob = new ObjetoColuna("Organizador frontal de cabo (Altura: 1U)", "Unid.", OF, OF*campo_n_pavimentos);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }


        if (area_reservada) {
            modelo_rack="Aberto";
            ob = new ObjetoColuna("Organizador lateral para rack " + String.valueOf(rack_informacoes[0]) + "U",
                    "Unid.", rack_informacoes[1]*2, rack_informacoes[1]*2*campo_n_pavimentos);
            ArmazenaListaObjetosColuna.lista.add(ob);
        } else {
            modelo_rack="Fechado";
            ob = new ObjetoColuna("Exaustor para rack fechado", "Unid.", rack_informacoes[1], rack_informacoes[1]*campo_n_pavimentos);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        ob = new ObjetoColuna("Bandeja fixa (Altura: 4U)", "Unid.", rack_informacoes[1], rack_informacoes[1]*campo_n_pavimentos);
        ArmazenaListaObjetosColuna.lista.add(ob);

        if (bandeja_deslizante) {
            ob = new ObjetoColuna("Bandeja deslizante (Altura: 2U)", "Unid.", rack_informacoes[1], rack_informacoes[1]*campo_n_pavimentos);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if (regua_fechamento) {
            int qtde_regua = rack_informacoes[0] * rack_informacoes[1] - (int) (rack_informacoes[0]/1.5);
            ob = new ObjetoColuna("Reguas de fechamento", "Unid.", qtde_regua, qtde_regua*campo_n_pavimentos);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if (DVR) {
            ob = new ObjetoColuna("DVR (Altura: 2U)", "Unid.", 1, 1);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        ob = new ObjetoColuna("Rack (" + modelo_rack + ") (Altura: " + rack_informacoes[0] + "U)", "Unid.", rack_informacoes[1], rack_informacoes[1]*campo_n_pavimentos);
        ArmazenaListaObjetosColuna.lista.add(ob);


    }

    public static void calcula_rack_seq() {
        int[] rack_informacoes = Calculos.calculaTamanhoRack(Calculos.tamanho_rack_seq+Calculos.tamanho_rack_set);
        ObjetoColuna ob;

        ob = new ObjetoColuna(" ", " ", " ", " ");
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("RACK SEQ/SET", " ", " ", " ");
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Distribuidor Óptico (DIO) 1U "+tamanho_dios+" portas", "Unid.", nro_dios, nro_dios);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Caixa de emenda", "Unid.", 2, 2*nro_dios);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Pigtail "+cat_fibra+" (Duplo) (Conector LC)", "Unid.", fibras_andar/2, (fibras_andar*(campo_n_pavimentos-1))/2);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Acoplador óptico (Duplo) (Conector LC)", "Unid.", fibras_andar/2, qtd_acop);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Cordão óptico "+cat_fibra+" (Duplo) (Conector LC) (2M)", "Unid.", fibras_andar/2, qtd_acop);
        ArmazenaListaObjetosColuna.lista.add(ob);

        if(!(ArmazenaEscolha.escolha.contains("malha"))) {
            if (area_reservada) {
                modelo_rack="Aberto";
                ob = new ObjetoColuna("Organizador lateral para rack " + String.valueOf(rack_informacoes[0]) + "U",
                        "Unid.", rack_informacoes[1]*2, rack_informacoes[1]*2);
                ArmazenaListaObjetosColuna.lista.add(ob);
            } else {
                modelo_rack="Fechado";
                ob = new ObjetoColuna("Exaustor para rack fechado", "Unid.", rack_informacoes[1], rack_informacoes[1]);
                ArmazenaListaObjetosColuna.lista.add(ob);
            }

            ob = new ObjetoColuna("Bandeja fixa (Altura: 4U)", "Unid.", rack_informacoes[1], rack_informacoes[1]);
            ArmazenaListaObjetosColuna.lista.add(ob);

            if (bandeja_deslizante) {
                ob = new ObjetoColuna("Bandeja deslizante (Altura: 2U)", "Unid.", rack_informacoes[1], rack_informacoes[1]);
                ArmazenaListaObjetosColuna.lista.add(ob);
            }

            if (regua_fechamento) {
                int qtde_regua = rack_informacoes[0] * rack_informacoes[1] - (int) (rack_informacoes[0]/1.5);
                ob = new ObjetoColuna("Reguas de fechamento", "Unid.", qtde_regua, qtde_regua);
                ArmazenaListaObjetosColuna.lista.add(ob);
            }
        }

        ob = new ObjetoColuna("Rack (" + modelo_rack + ") (" + rack_informacoes[0] + "U)", "Unid.", rack_informacoes[1], rack_informacoes[1]);
        ArmazenaListaObjetosColuna.lista.add(ob);


    }

    public static void calcula_backbone_externo() {
        int tam_cabo = (int) Math.ceil((distancia_externo + 30) * 1.1);
        final int[] tamanhos_dio = {12, 16, 24, 48};
        int dio_escolhido = 48;

        for (int i = 0; i < 4; i++) {
            if (tamanhos_dio[i] >= qtd_pares_externo) {
                dio_escolhido = tamanhos_dio[i];
                break;
            }
        }

        ObjetoColuna ob = new ObjetoColuna(" ", " ", " ", " ");
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Cabeamento de backbone externo", " ", " ", " ");
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Cabo óptico "+qtd_pares_externo*2+" fibras 9x125 loose tube", "m", tam_cabo, tam_cabo);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Distribuidor Óptico (DIO) 1U "+dio_escolhido+" portas", "Unid.", 1, 2);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Caixa de emenda", "Unid.", 2, 4);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Pigtail 9x125 (Duplo) (Conector LC)", "Unid.", qtd_pares_externo, 2*qtd_pares_externo);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Acoplador óptico (Duplo) (Conector LC)", "Unid.", qtd_pares_externo, 2*qtd_pares_externo);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Cordão óptico 9x125 (Duplo) (Conector LC) (2M)", "Unid.", qtd_pares_externo, 2*qtd_pares_externo);
    }
}
