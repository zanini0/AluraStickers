import static java.net.http.HttpRequest.newBuilder;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;


public class App {
    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (título, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDefilmes = parser.parse(body);

        // exibir e manipular os dados
        for (int i = 0; i < 3; i++) {
            Map<String, String> filmes = listaDefilmes.get(i);

            String urlImagem = filmes.get("image");
            String titulo = filmes.get("title").replace(":", "-");

            InputStream inputStream = new URL(urlImagem).openStream();
            String nomeArquivo = titulo +".png";

            var geradora = new GeradoraDeFigurinhas();
            geradora.cria(inputStream, nomeArquivo);

            System.out.println("\u001b[1mTítulo:\u001b[m" + filmes.get("fullTitle"));
            System.out.println();
            Double classificacao = Double.parseDouble((filmes.get("imDbRating"))); 
                for (int n = 1; n<= classificacao; n++) {
                System.out.print("⭐");
            }
            System.out.println();
            System.out.println(escalaDeRating(Integer.parseInt(filmes.get("imDbRatingCount"))));

            System.out.println("\n");
            }

        
        }
    

    public static String escalaDeRating(int n) {
        if (n >= 1000000) {
            return  "🥇";
        } else if (n >= 1000) {
            return "🥈";
        } else if (n >= 100) {
            return "🥉";
        } else
            return "" + n;

    }
}
