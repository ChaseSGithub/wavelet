import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class searchHandler implements URLHandler {

    ArrayList<String> strList = new ArrayList<String>(); 

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return String.format("No more numbers");
        } else if (url.getPath().equals("/clearList")) {

            strList.clear();
            return String.format("Your List is now empty!");
            
        } else {
            System.out.println("Path: " + url.getPath());
            if (url.getPath().contains("/add")) {
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")) {
                    strList.add(parameters[1]);
                    return String.format("%s has been adding to the list! The list currently contains %s", parameters[1], strList);
                }
            }
            if (url.getPath().contains("/search")) {
                ArrayList<String> correctQuery = new ArrayList<String>();
                
                String[] parameters = url.getQuery().split("=");
                if (parameters[0].equals("s")){
                    for (int n=0; n<strList.size(); n++){
                        if((strList.get(n)).contains(parameters[1])){
                            correctQuery.add(strList.get(n));
                        }
                    }
                    return String.format("the items that match your query are %s", correctQuery);
                }
            }
            return "404 Not Found!";
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

        Server.start(port, new searchHandler());
    }
}
