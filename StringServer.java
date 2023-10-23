import java.io.IOException;
import java.net.URI;

class StringHandler implements URLHandler {
    private StringBuilder message = new StringBuilder();
    private int sequence = 0;

    public String handleRequest(URI url) {
        if (url.getPath().equals("/")) {
            return message.toString();
        } else if (url.getPath().equals("/add-message")) {
            String query = url.getQuery();
            if (query != null && query.startsWith("s=")) {
                String newMessage = query.substring(2);
                sequence++;
                message.append(sequence).append(". ").append(newMessage).append("\n");
                return message.toString();
            } else {
                return "Invalid request!";
            }
        } else {
            return "404 Not Found!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }

        int port = Integer.parseInt(args[0]);

        Server.start(port, new StringHandler());
    }
}