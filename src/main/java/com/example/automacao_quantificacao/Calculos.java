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

    private static boolean regua_fechamento = false;
    private static String cat_fibra;
    private static int pe_direito;
    private static int backbones_andar;
    private static int fibras_andar;

    public static void setRegua(boolean valor) {
        regua_fechamento = valor;
    }

    public static void setFibra(String valor) {
        cat_fibra = valor;
    }

    public static void pe_direito(int valor) {
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

        //Conversoes e variaveis
        Integer tomadas = 0;
        Integer seg = 0;
        Integer voz = 0;
        Integer dados = campo_pts_pavimentos*2;

        if (!((campo_seg == null || campo_seg.isEmpty()) || campo_seg.equals("0")))
            seg = Integer.parseInt(campo_seg);

        if (!((campo_voz == null || campo_voz.isEmpty()) || campo_voz.equals("0")))
            voz = Integer.parseInt(campo_voz);

        tomadas = campo_pts_pavimentos*2 + seg;

        Integer pav = campo_n_pavimentos;
        Integer malha_horizontal = campo_dt_malha * tomadas;
        Integer PP = (int) Math.ceil(tomadas/24.0);
        //Etiqueta de identificação de tomadas e espelhos
        Integer ETITE = campo_pts_pavimentos*3 + seg*2;
        //Filtro de linha
        Integer OF = 2*PP;
        double tamanho_rack = 0.0;
        int[] rack_informacoes;
        Integer tamanho_bandeja;

        if (bandeja_deslizante)
            tamanho_bandeja = 6;
        else
            tamanho_bandeja = 4;

        String modelo_rack;

        if (area_reservada)
            modelo_rack = "Aberto";
        else
            modelo_rack = "Fechado";

        String categoria_cabo = categoria;

        //Quantificacao das tomadas
        ObjetoColuna ob = new ObjetoColuna("Tomada RJ-45 fêmea (" + categoria_cabo + ")", "Unid.", tomadas, pav*tomadas);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos espelhos


        ob = new ObjetoColuna("Espelho de conexão 2x4", "Unid.", dados/2, (dados/2)*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        if (seg != 0) {
            ob = new ObjetoColuna("Espelho de conexão 2x2", "Unid.", seg, seg*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);

            ob = new ObjetoColuna("Cordão de ligação (Patch Cord), (Cor: do teto) (Tamanho: 1M) (" + categoria_cabo + ")", "Unid.", seg, seg*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        //Quantificacao dos patch cords
        ob = new ObjetoColuna("Cordão de ligação (Patch cord), (Cor: Azul) (Tamanho: 3M) (" + categoria_cabo + ")", "Unid.", dados, dados*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao cabo UTP rigido

        malha_horizontal = (int) Math.ceil((double) malha_horizontal/305);
        ob = new ObjetoColuna("Cabo UTP rígido para malha horizontal (" + categoria_cabo + ")", "Cxs", malha_horizontal, malha_horizontal*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos patch cables

        int pc = tomadas-voz-seg;

        if(voz != 0) {
            ob = new ObjetoColuna("Patch Cable (Cor: Amarelo) (Tamanho: 2M) (" + categoria_cabo + ")", "Unid.", voz, voz * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(seg != 0) {
            ob = new ObjetoColuna("Patch Cable (Cor: Vermelho) (Tamanho: 2M) (" +categoria_cabo + ")", "Unid.", seg, seg * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        ob = new ObjetoColuna("Patch Cable (Cor: Azul) (Tamanho: 2M) ("+categoria_cabo+")", "Unid.", pc, pc*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Patch Panel
        ob = new ObjetoColuna("Painel de telecomunicações 24 portas (Altura: 1U) (" + categoria_cabo + ")", "Unid.", PP, PP*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //SwitcheS (mesma quantidade de PP)
        ob = new ObjetoColuna("Switch 24 portas (Altura: 1U)", "Unid.", PP, PP*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Organizadores frontais de cabos
        ob = new ObjetoColuna("Organizador frontal de cabo (Altura: 1U)", "Unid.", OF, OF*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Montagem do rack
        if (DVR) {
            tamanho_rack += 2;
            ob = new ObjetoColuna("DVR (Altura: 2U)", "Unid.", 1, pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        tamanho_rack = (double) ((PP*2) + OF + tamanho_bandeja);
        tamanho_rack = tamanho_rack*1.5;
        rack_informacoes = calculaTamanhoRack(tamanho_rack);

        ob = new ObjetoColuna("Bandeja fixa (Altura: 4U)", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        if (bandeja_deslizante) {
            ob = new ObjetoColuna("Bandeja deslizante (Altura: 2U)", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if (area_reservada) {
            modelo_rack="Aberto";
            ob = new ObjetoColuna("Organizador lateral para rack " + String.valueOf(rack_informacoes[0]) + "U",
                    "Unid.", rack_informacoes[1]*2, rack_informacoes[1]*2*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        } else {
            modelo_rack="Fechado";
            ob = new ObjetoColuna("Exaustor para rack fechado", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        ob = new ObjetoColuna("Rack (" + modelo_rack + ")" + "(Tamanho: " + String.valueOf(rack_informacoes[0]) + "U)", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Miscelanea
        ob = new ObjetoColuna("Etiquetas para tomadas e espelhos", "Unid.", ETITE, ETITE*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiqueta para identificação do cabo de malha horizontal", "Unid.", tomadas*2, (tomadas*2) * pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para identificação de portas do Patch Panel", "Unid.", PP*24, (PP*24) * pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para Rack", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para SW e PP", "Unid.", PP*2, (PP*2) * pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para identificação dos Patch Cables", "Unid.", tomadas*2, (tomadas*2) * pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Abraçadeira de velcro", "m", rack_informacoes[1]*3, rack_informacoes[1]*3*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Abraçadeira Hellermann", "Conj.", rack_informacoes[1], rack_informacoes[1]*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Filtro de linha 6 tomadas", "Unid.", rack_informacoes[1], rack_informacoes[1]*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Parafuso Porca Gaiola (Conjunto com 10 unidades)", "Conj.", (int) Math.ceil(rack_informacoes[0]*4*rack_informacoes[1]/10.0), (int) Math.ceil(rack_informacoes[0]*4*rack_informacoes[1]/10.0)*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        if (regua_fechamento) {
            int qtde_regua = rack_informacoes[0] * rack_informacoes[1] - (int) (tamanho_rack/1.5);
            ob = new ObjetoColuna("Reguas de fechamento", "Unid.", qtde_regua, qtde_regua*pav);

            ArmazenaListaObjetosColuna.lista.add(ob);
        }
    }


    public static void calculafibra() {
        //escreva o calculo da fibra aqui
    }


    public static int[] calculaTamanhoRack(double tamanho_rack) {

        double[] tamanhos_rack = {4.0, 6.0, 8.0, 10.0, 12.0, 16.0, 20.0, 24.0, 28.0, 32.0, 36.0, 40.0, 44.0};
        int quantidade_rack = 1;
        int[] vetor = new int[2];

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
}
