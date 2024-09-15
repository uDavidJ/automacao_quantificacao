package com.example.automacao_quantificacao;

public class Calculos {

    public static int campo_pts_pavimentos;
    public static int campo_n_pavimentos;
    public static int campo_dt_malha;
    public static String categoria;
    public static String campo_voz;
    public static String campo_seg;
    public static String campo_dados;
    public static boolean bandeja_fixa = false;
    public static boolean bandeja_deslizante = false;
    public static boolean DVR = false;
    public static boolean area_reservada = false;



    public static void calcula() {

        //Conversoes e variaveis
        Integer tomadas = campo_pts_pavimentos * 2;
        Integer pav = campo_n_pavimentos;
        Integer patchCords = tomadas;
        Integer malha_horizontal = campo_dt_malha * tomadas;
        Integer PP;
        Integer PG;
        //Etiqueta de identificação de tomadas e espelhos
        Integer ETITE;
        //Etiqueta de identificação de malha horizontal
        Integer ETIMH;
        //Etiquetas de identificação de portas de patch panel
        Integer EIPPP;
        //Etiquetas de identificação dos patch cables
        Integer EIPC;
        //Etiquetas de identificação de rack
        Integer EIR;
        //Etiquetas para switch e pp
        Integer EISWPP;
        //Filtro de linha
        Integer OF;
        double tamanho_rack = 0.0;
        int[] rack_informacoes;
        Integer tamanho_bandeja;
        String modelo_rack;
        String categoria_cabo = categoria;


//        ObjetoColuna ob = new ObjetoColuna("Área de trabalho", "",0, 0);
//        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao das tomadas
        ObjetoColuna ob = new ObjetoColuna("Tomada RJ-45 fêmea (" + categoria_cabo + ")", "Unid.",
                tomadas,
                pav*tomadas);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos espelhos
        ob = new ObjetoColuna("Espelho de conexão 2x4 (" + categoria_cabo + ")", "Unid.",
                tomadas/2,
                (tomadas/2)*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos patch cords
        if(!(campo_voz == "" || campo_voz == "0"))
            patchCords = patchCords - Integer.parseInt(campo_voz);

        ob = new ObjetoColuna("Cordão de ligação (Patch cord), (Cor: Azul) (Tamanho: 3M) (" + categoria_cabo + ")", "Unid.",
                patchCords,
                tomadas*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao cabo UTP rigido

        if(malha_horizontal%305 != 0)
            malha_horizontal=(malha_horizontal/305)+1;
        else
            malha_horizontal=malha_horizontal/305;

        ob = new ObjetoColuna("Cabo UTP rígido para malha horizontal (" + categoria_cabo + ")", "Cxs",
                malha_horizontal,
                malha_horizontal*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Quantificacao dos patch cables
        if(!(campo_voz == "" || campo_voz == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Amarelo) (Tamanho: 2M) (" + categoria_cabo + ")", "Unid.",
                    Integer.parseInt(campo_voz),
                    Integer.parseInt(campo_voz) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(!(campo_seg == "" || campo_seg == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Vermelho) (Tamanho: 2M) (" +categoria_cabo + ")", "Unid.",
                    Integer.parseInt(campo_seg),
                    Integer.parseInt(campo_seg) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        if(!(campo_dados == "" || campo_dados == "0")) {
            ob = new ObjetoColuna("Patch Cable (Cor: Azul) (Tamanho: 2M) (" + categoria_cabo + ")", "Unid.",
                    Integer.parseInt(campo_dados),
                    Integer.parseInt(campo_dados) * pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }

        //Patch Panel
        if(tomadas%24 != 0)
            PP = (tomadas/24)+1;
        else
            PP = tomadas/24;

        ob = new ObjetoColuna("Painel de telecomunicações 24 portas (Altura: 1U) (" + categoria_cabo + ")", "Unid.",
                PP,
                PP*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //SwitcheS (mesma quantidade de PP)
        ob = new ObjetoColuna("Switch 24 portas (Altura: 1U)", "Unid.",
                PP,
                PP*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        //Organizadores frontais de cabos
        OF = 2*PP;
        ob = new ObjetoColuna("Organizador frontal de cabo (Altura: 1U)", "Unid.",
                OF,
                OF*pav);
        ArmazenaListaObjetosColuna.lista.add(ob);

        //Montagem do rack
        if(bandeja_fixa)
            tamanho_bandeja=4;
        else
            tamanho_bandeja=3;

        tamanho_rack = (double) ((PP*2) + OF + tamanho_bandeja);

        if (DVR)
            tamanho_rack+=2;

        tamanho_rack = tamanho_rack*1.5;
        rack_informacoes = calculaTamanhoRack(tamanho_rack);

        if(area_reservada)
        {
            modelo_rack="Aberto";
            ob = new ObjetoColuna("Organizador lateral para rack " + String.valueOf(rack_informacoes[0]) + "U",
                    "Unid.", rack_informacoes[1]*2, rack_informacoes[1]*pav);
            ArmazenaListaObjetosColuna.lista.add(ob);
        }
        else
            modelo_rack="Fechado";

        ob = new ObjetoColuna("Rack (" + modelo_rack + ")" + "(Tamanho: " +
                String.valueOf(rack_informacoes[0]) + "U)", "Unid.",
                rack_informacoes[1],
                rack_informacoes[1]*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para identificação de tomadas e espelhos",
                "Unid.",
                tomadas + (tomadas/2),
                (tomadas + (tomadas/2)) * pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiqueta para identificação do cabo de malha horizontal",
                "Unid.",
                tomadas*2,
                (tomadas*2) * pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para identificação de portas do Patch Panel",
                "Unid.",
                PP*24,
                (PP*24) * pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para Rack", "Unid.",
                rack_informacoes[1],
                rack_informacoes[1]*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para SW e PP", "Unid.",
                PP*2,
                (PP*2) * pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Etiquetas para identificação dos Patch Cables", "Unid.",
                tomadas*2,
                (tomadas*2) * pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Abraçadeira de velcro", "m",
                rack_informacoes[1]*3,
                rack_informacoes[1]*3*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Abraçadeira Hellermann", "Conj.",
                rack_informacoes[1],
                rack_informacoes[1]*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Filtro de linha 6 tomadas", "Unid.",
                rack_informacoes[1],
                rack_informacoes[1]*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

        ob = new ObjetoColuna("Parafuso Porca Gaiola (Conjunto com 10 unidades)", "Conj.",
                rack_informacoes[0]*4*rack_informacoes[1],
                rack_informacoes[0]*4*rack_informacoes[1]*pav);

        ArmazenaListaObjetosColuna.lista.add(ob);

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
