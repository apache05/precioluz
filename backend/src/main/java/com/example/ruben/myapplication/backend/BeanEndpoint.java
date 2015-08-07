package com.example.ruben.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.Random;
import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "beanApi",
        version = "v1",
        resource = "bean",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.ruben.example.com",
                ownerName = "backend.myapplication.ruben.example.com",
                packagePath = ""
        )
)
public class BeanEndpoint {
    private static final Logger logger = Logger.getLogger(BeanEndpoint.class.getName());


    @ApiMethod(name = "getBean")
    public Bean getBean(@Named ("fecha") String fecha) {
        // TODO: Implement this function
        logger.info("Calling getBean method");
        Float[] lista_precios= new Float[72];

        Random rnd = new Random();
        for (int i=0; i<72; i++){
            lista_precios[i] = rnd.nextFloat();
        }
        Bean un_Bean= new Bean();
        un_Bean.setListapreciostodastarifas(lista_precios);
        return un_Bean;
    }

    /**
     * This inserts a new <code>Bean</code> object.
     *
     * @param bean The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertBean")
    public Bean insertBean(Bean bean) {
        // TODO: Implement this function
        logger.info("Calling insertBean method");
        return bean;
    }
}