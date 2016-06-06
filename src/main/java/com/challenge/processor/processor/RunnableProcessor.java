package com.challenge.processor.processor;

import com.challenge.processor.ProcessingException;

public class RunnableProcessor implements Runnable{

    private final ProcessorInterface processor;

    public RunnableProcessor(ProcessorInterface processor){
        this.processor = processor;
    }

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
    @Override
    public void run() {
        try {
            processor.process();
        } catch (ProcessingException e) {
            // TODO handle this case
            e.printStackTrace();
        }
    }
}
