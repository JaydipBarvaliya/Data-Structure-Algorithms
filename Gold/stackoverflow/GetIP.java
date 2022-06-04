package com.stackoverflow;

public class GetIP {
    static String url; //global so I can access it after the threads are finished

    public class CheckIP extends Thread {
        private String url_test;

        public CheckIP(String url_t) {
            url_test = url_t;
        }

        public void run() {
            try {
                //result = getHTML(this.url_test);  //result = the response from the GET request to this.url_test
            } catch (Exception e) {

            }

//            if (result < is what I want >){
//                url = this.url_test
//                System.out.println("Flag 1"); //<I'd like to do something here, preferebly kill all other threads that are trying to connect to an 'unserved' URL>
//            }
            System.out.println("Flag 1");
        }
    }

     public static void main(String[] args) throws Exception{

        String ip_partial = "my computer's IP without the last part - ex: 192.168.0. , I'll hide the functions to make it short";

        Thread myThreads[] = new Thread[254];
        for (int i = 1; i < 255; i++) {
            String url_test="http://"+ip_partial+i+":<port + endpoint>";
            GetIP getip = new GetIP ();
            myThreads[i] = new Thread(getip.new CheckIP(url_test));
            myThreads[i].start();
        }
        for (int i = 1; i < 254; i++) {
            System.out.println("Flag 2");
            myThreads[i].join(); //todo add catch exception
        }
    }
}
