package example;

import javax.xml.xpath.XPathExpressionException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import example.XmlWripper;
import javax.swing.JOptionPane;
import java.util.concurrent.TimeUnit;

public class Example {
    public static void main(String[] args) {
        System.out.println("===PARSER===");
        XmlWripper xml = new XmlWripper("./teste.xml");

        Node no = xml.getNode("/teste/url");


        WebDriver driver = new FirefoxDriver();
        driver.get(no.getTextContent());

        NodeList nodeList = xml.getNodeList("/teste/element");

        WebElement element = null;//driver.findElement(By.name("q"));



        for (int i = 0; i < nodeList.getLength(); i++) {
            Element item = (Element)nodeList.item(i);

            //Node no2 = xml.getNode("findBy", item);

            //System.out.println(no2.getAttributes().getNamedItem("type"));

            //NodeList lstAction = xml.getNodeList('action', item);
//					for (int i = 0; i < nodeList.getLength(); i++) {
//						
//					}

            /*
                Reconhecendo tag de como o teste quer pegar o elemento, por ID, CLASS, NAME
             */
            try {
                /**
                 * Pegando tag findById QUE ESTA NO XML
                 * e validando se a tag nao existe ou esta vazia, e criando elemento do selenium como name
                 */
                String nome = xml.xPath.evaluate("findByName", item);
                if( nome != null && !nome.isEmpty() ){
                    System.out.println("findByName--> "+nome );
                    element = driver.findElement(By.name(nome));
                }

                /**
                 * Pegando tag findByClass QUE ESTA NO XML
                 * e validando se a tag nao existe ou esta vazia, e criando elemento do selenium como name
                 */
                String byclass = xml.xPath.evaluate("findByClass", item);
                if( byclass != null && !byclass.isEmpty() ){
                    try{
                    System.out.println("findByClass--> "+byclass );
                    element = driver.findElement(By.className(byclass));
                }catch(Exception ee){
                    System.out.println("Não esta present");
                }
                }

                /**
                 * Pegando tag findById QUE ESTA NO XML
                 * e validando se a tag nao existe ou esta vazia, e criando elemento do selenium como name
                 * validando se o elemento existe ou nao existe no brawser
                 */
                String id = xml.xPath.evaluate("findById", item);
                if( id != null && !id.isEmpty() ){

                    System.out.println("findById--> "+id );
                    try{
                        element = driver.findElement(By.id(id));
                    }catch(Exception ee){
                        System.out.println("Não esta present");
                    }

                }



                String isXpathElement = xml.xPath.evaluate("isXpathElement", item);
                String isXpathAtribute = xml.xPath.evaluate("isXpathAtribute", item);
                String isXpathValue = xml.xPath.evaluate("isXpathValue", item);
                if(isXpathElement != null && !isXpathElement.isEmpty()){
                    System.out.println("findByTitle--> "+isXpathElement );
                    element = driver.findElement(By.xpath("//"+isXpathElement+"[@"+isXpathAtribute+"='"+isXpathValue+"']"));
                }



                   String isEnable = xml.xPath.evaluate("isEnabled", item);
                if( isEnable != null && !isEnable.isEmpty() ){
                    Boolean validade = element.isEnabled();
                    if(validade == true){
                        System.out.print("Estar habilitado \n");
                    }else if(validade == false){
                        System.out.print(" Desabilitado \n");
                    }

                }


                /*
                    AÇÕES QUE O ELEMENTO TEM QUE FAZER  sendkey = escrever, click = clicar no elemento, submit = submeter um formulario
                 */
                String sendKey = xml.xPath.evaluate("sendKey", item);
                if( sendKey != null && !sendKey.isEmpty() ){
                    element.sendKeys(sendKey);
                    System.out.println("sendKey--> "+sendKey );

                }

                String click = xml.xPath.evaluate("click", item);
                if( click != null && !click.isEmpty()){
                    element.click();
                    driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
                   // driver.manager().timeouts().implicityWait(15);
                 //   driver.sleep(200);
                    System.out.println("CLick--> " + click);

                }

                String submit = xml.xPath.evaluate("submit", item);
                if(submit != null && !submit.isEmpty()) {
                    element.submit();
                    System.out.println("Submit-->");
                }



                System.out.println("\n--------------------------------------------------------------------------------------");
            } catch (XPathExpressionException e) {
                e.printStackTrace();
            }
        }
    }
}