package com.example.ruben.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsInputChannel;

import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.NonRetriableException;
import com.google.appengine.tools.cloudstorage.RetryParams;


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

    @ApiMethod(name = "getBean")
    public Bean getBean(@Named ("fecha") String fecha) {
        Bean un_bean= new Bean();
        return un_bean;
    }

    /**
     * This inserts a new <code>Bean</code> object.
     *
     * @param bean The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertBean")
    public Bean insertBean(Bean bean) {
        return bean;
    }
}