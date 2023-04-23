import java.io.IOException;
import java.net.URI;

class Handler implements URLHandler {
    String str = "";
    public String handleRequest(URI url){
        if (url.getPath().equals("/")){
            return str;
        }
        else if(url.getPath().contains("/add-message")){
            String[] input = url.getQuery().split("=");
            str = str + "\n" + input[1];
            return str;
        }
        else{return "404 Not Found";}
    }

}
    
class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}
