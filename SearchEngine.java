import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    public ArrayList<String> lst = new ArrayList<>();

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("List: " + lst);
        }
        else if(url.getPath().contains("/add")){
            String[] parameters = url.getQuery().split("=");
            lst.add(parameters[1]);
            return String.format("Word Added!: " + lst);
        }
        else if(url.getPath().contains("/search")){
            String[] parameters = url.getQuery().split("=");
            String toFind = parameters[1];
            ArrayList<String> temp = new ArrayList<>();
            for (String s:lst){
                if (s.contains(toFind)) {
                    temp.add(s);
                }
            }
            return String.format("Search results: " + temp);
        }
        else{
            return "404 Not Found.";
        }
    }

}

class SearchEngine {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}