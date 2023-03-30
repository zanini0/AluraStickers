import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class GeradoraDeFigurinhas {
    

    void cria(InputStream inputStream, String nomeArquivo) throws Exception {

        // leitura da imagem
        //InputStream inputStream = new FileInputStream(new File("entrada/filme.jpg"));
        // InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        //
        BufferedImage imagemOriginal = ImageIO.read(inputStream);

        // cria nova imagem em mémoria com transparência e com tamanho novo
        int largura = imagemOriginal.getWidth();
        int altura = imagemOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage novaImagem = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        // copiar a imagem original para nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) novaImagem.getGraphics();
        graphics.drawImage(imagemOriginal, 0, 0, null);
        
        //configurar a fonte
        var fonte = new Font(java.awt.Font.SANS_SERIF, java.awt.Font.BOLD, 64);
        graphics.setColor(Color.YELLOW);
        graphics.setFont(fonte);

         // escrever uma frase em uma nova imagem
        graphics.drawString("TOPZERA", 200, novaAltura - 100);


        // escrever a nova imagem em um arquivo
        // File file = new File("saida");
        // if (!file.exists()){
        // file.mkdir();
        // }


        ImageIO.write(novaImagem, "png", new File("saida/" + nomeArquivo ));
    }

    // public static void main(String[] args) throws Exception {
    //     var geradora = new GeradoraDeFigurinhas();
    //     geradora.cria("abacate", "banana");
    // }

}
