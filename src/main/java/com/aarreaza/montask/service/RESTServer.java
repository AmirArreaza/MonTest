package com.aarreaza.montask.service;

import static spark.Spark.get;
import static spark.Spark.init;

public class RESTServer implements Runnable {
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    public void run() {
        init();
        loadServices();
    }

    private void loadServices(){
        get("/hello", (req, res) -> "Hello");
    }
}
