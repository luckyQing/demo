package com.liyulin.skills.thread;

/**
 * 异步
 * 
 * @author liyulin
 * @date 2013/03/08
 */
public class SynDemo {
	
    public static void main(String[] args) {
        Share share = new Share();
        new Caller(share,"A");
        new Caller(share,"B");
        new Caller(share,"C");
    }
}

class Share{
    void print(String str){
        System.out.print("["+str);
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
        }
        System.out.println("]");
    }
}

class Caller implements Runnable{
    String str;
    Share share;
    Thread thread;
    public Caller(Share share, String str){
        this.share = share;
        this.str = str;
        thread = new Thread(this);
        thread.start();
    }
    
    public void run(){
        share.print(str);
    }
}

