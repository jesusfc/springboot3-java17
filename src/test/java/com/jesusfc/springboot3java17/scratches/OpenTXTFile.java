package com.jesusfc.springboot3java17.scratches;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
class OpenTXTFile {


    private static final String FOLDER_PRODUCT_CODE_TXT_FILE = "/home/jesusfc/Escritorio/trainer_product_10-10-2023.txt";

    public static void main(String[] args) {
        List<String> trainerIdProductId = openSingleTXTFile();

        AtomicInteger idx = new AtomicInteger();
        trainerIdProductId.forEach(st -> {
            System.out.println(idx.incrementAndGet() + "- " + st);
            String[] split = st.split(",");
            Long trainerId = Long.parseLong(split[0]);
            Long productId = Long.parseLong(split[1]);
        });
    }

    private static List<String> openSingleTXTFile() {

        List<String> result = new ArrayList<>();

        File archivo;
        FileReader fr = null;
        BufferedReader br;

        try {
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            archivo = new File(FOLDER_PRODUCT_CODE_TXT_FILE);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero
            String linea;
            while ((linea = br.readLine()) != null) {
                String s = StringUtils.substringBetween(linea, "(", ")");
                result.add(s);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            // En el finally cerramos el fichero, para asegurarnos
            // que se cierra tanto si todo va bien como si salta
            // una excepcion.
            try {
                if (null != fr) fr.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
        return result;
    }
}