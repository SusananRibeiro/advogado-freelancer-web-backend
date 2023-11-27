package com.advogado.freelancer.frameWork;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConversorData {
    public static String converterDataBrasileiraParaDataAmericana(String dataBrasileira) {
        SimpleDateFormat formatoBrasileiro = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat formatoAmericano = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date data = formatoBrasileiro.parse(dataBrasileira);
            return formatoAmericano.format(data);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
