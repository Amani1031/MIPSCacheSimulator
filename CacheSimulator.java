//Names: Amani Arora, Hank Wai


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class CacheSimulator {

    public static class ConversionNode{
        char hex;
        String binary;

        public ConversionNode(char hex, String binary){
            this.hex = hex;
            this.binary = binary;
        }
    }

    public static ArrayList<ConversionNode> conversionInfo = new ArrayList<>();

    public static class Associate2Node{
        int tag1;
        int lastLineTag1;
        int tag2;
        int lastLineTag2;

        public Associate2Node(int tag1, int lastLineTag1, int tag2, int lastLineTag2){
            this.tag1 = tag1;
            this.lastLineTag1 = lastLineTag1;
            this.tag2 = tag2;
            this.lastLineTag2 = lastLineTag2;
        }
    }

    public static class Associate4Node{
        int tag1, tag2, tag3, tag4;
        int lastLineTag1, lastLineTag2, lastLineTag3, lastLineTag4;

        public Associate4Node(int tag1, int lastLineTag1, int tag2, int lastLineTag2,
                              int tag3, int lastLineTag3, int tag4, int lastLineTag4){
            this.tag1 = tag1;
            this.lastLineTag1 = lastLineTag1;
            this.tag2 = tag2;
            this.lastLineTag2 = lastLineTag2;
            this.tag3 = tag3;
            this.lastLineTag3 = lastLineTag3;
            this.tag4 = tag4;
            this.lastLineTag4 = lastLineTag4;
        }
    }

    public static int[] configOne = new int[512];
    public static int[] configTwo = new int[256];
    public static int[] configThree = new int[128];
    public static Associate2Node[] configFour = new Associate2Node[256];
    public static Associate4Node[] configFive = new Associate4Node[128];
    public static Associate4Node[] configSix = new Associate4Node[32];
    public static int[] configSeven = new int[1024];

    public static int[] hits = new int[7];

    public static int totalAccesses;

    public static void main(String[] args) throws Exception {
        initialiseConversionInfo();
        initialiseHitArray();
        initialiseAssociate2Array();
        initialiseAssociate4Array();

        InputStream inputStream = new FileInputStream(args[0]);
        Scanner sc = new Scanner(inputStream);
        int lineNumber = 1;
        while (sc.hasNext()) {
            launchCache(sc.nextLine(), lineNumber);
            lineNumber++;
        }
        sc.close();
        displayCacheResults();
    }

    public static void initialiseConversionInfo(){
        conversionInfo.add(new ConversionNode('0',"0000"));
        conversionInfo.add(new ConversionNode('1',"0001"));
        conversionInfo.add(new ConversionNode('2',"0010"));
        conversionInfo.add(new ConversionNode('3',"0011"));
        conversionInfo.add(new ConversionNode('4',"0100"));
        conversionInfo.add(new ConversionNode('5',"0101"));
        conversionInfo.add(new ConversionNode('6',"0110"));
        conversionInfo.add(new ConversionNode('7',"0111"));
        conversionInfo.add(new ConversionNode('8',"1000"));
        conversionInfo.add(new ConversionNode('9',"1001"));
        conversionInfo.add(new ConversionNode('a',"1010"));
        conversionInfo.add(new ConversionNode('b',"1011"));
        conversionInfo.add(new ConversionNode('c',"1100"));
        conversionInfo.add(new ConversionNode('d',"1101"));
        conversionInfo.add(new ConversionNode('e',"1110"));
        conversionInfo.add(new ConversionNode('f',"1111"));
    }

    public static void initialiseHitArray(){
        hits[0] = 0;
        hits[1] = 0;
        hits[2] = 0;
        hits[3] = 0;
        hits[4] = 0;
        hits[5] = 0;
        hits[6] = 0;
    }

    public static void initialiseAssociate2Array(){
        for (int i = 0 ; i < 256 ; i++){
            configFour[i] = new Associate2Node(-1, -1, -1, -1);
        }
    }

    public static void initialiseAssociate4Array(){
        for (int i = 0 ; i < 128 ; i++){
            configFive[i] = new Associate4Node(-1, -1, -1, -1, -1,
                                    -1, -1, -1);
            if (i < 32){
                configSix[i] = new Associate4Node(-1, -1, -1, -1, -1,
                        -1, -1, -1);
            }
        }
    }

    public static void displayCacheResults(){
        double hitRate = (((double) hits[0]) / totalAccesses) * 100;
        System.out.println("Cache #1");
        System.out.println("Cache size: 2048B       Associativity: 1        Block size: 1");
        System.out.println("Hits: " + hits[0] + "   Hit Rate: " + String.format("%.2f",hitRate) + "%");
        System.out.println("---------------------------");
        hitRate = (((double) hits[1]) / totalAccesses) * 100;
        System.out.println("Cache #2");
        System.out.println("Cache size: 2048B       Associativity: 1        Block size: 2");
        System.out.println("Hits: " + hits[1] + "   Hit Rate: " + String.format("%.2f",hitRate) + "%");
        System.out.println("---------------------------");
        hitRate = (((double) hits[2]) / totalAccesses) * 100;
        System.out.println("Cache #3");
        System.out.println("Cache size: 2048B       Associativity: 1        Block size: 4");
        System.out.println("Hits: " + hits[2] + "   Hit Rate: " + String.format("%.2f",hitRate) + "%");
        System.out.println("---------------------------");
        hitRate = (((double) hits[3]) / totalAccesses) * 100;
        System.out.println("Cache #4");
        System.out.println("Cache size: 2048B       Associativity: 2        Block size: 1");
        System.out.println("Hits: " + hits[3] + "   Hit Rate: " + String.format("%.2f",hitRate) + "%");
        System.out.println("---------------------------");
        hitRate = (((double) hits[4]) / totalAccesses) * 100;
        System.out.println("Cache #5");
        System.out.println("Cache size: 2048B       Associativity: 4        Block size: 1");
        System.out.println("Hits: " + hits[4] + "   Hit Rate: " + String.format("%.2f",hitRate) + "%");
        System.out.println("---------------------------");
        hitRate = (((double) hits[5]) / totalAccesses) * 100;
        System.out.println("Cache #6");
        System.out.println("Cache size: 2048B       Associativity: 4        Block size: 4");
        System.out.println("Hits: " + hits[5] + "   Hit Rate: " + String.format("%.2f",hitRate) + "%");
        System.out.println("---------------------------");
        hitRate = (((double) hits[6]) / totalAccesses) * 100;
        System.out.println("Cache #7");
        System.out.println("Cache size: 4096B       Associativity: 1        Block size: 1");
        System.out.println("Hits: " + hits[6] + "   Hit Rate: " + String.format("%.2f",hitRate) + "%");
        System.out.println("---------------------------");
    }

    public static void launchCache(String addr, int lineNumber){
        addr = addr.substring(1);
        addr = hexToBinary(addr.trim());
        totalAccesses++;
        updateDirectConfig(configOne, 1,
                isolateTag(addr,21),
                isolateIndex(addr,21, 9));

        updateDirectConfig(configTwo,2,
                isolateTag(addr,21),
                isolateIndex(addr,21, 8));

        updateDirectConfig(configThree,3,
                isolateTag(addr,21),
                isolateIndex(addr,21, 7));

        updateAssociate2Config(configFour,4,
                isolateTag(addr,22),
                isolateIndex(addr, 22, 8), lineNumber);

        updateAssociate4Config(configFive,5,
                isolateTag(addr,23),
                isolateIndex(addr, 23, 7), lineNumber);

        updateAssociate4Config(configSix,6,
                isolateTag(addr,23),
                isolateIndex(addr, 23, 5), lineNumber);

        updateDirectConfig(configSeven,7,
                isolateTag(addr,20),
                isolateIndex(addr,20, 10));
    }

    public static int isolateTag(String addr, int bits){
        return Integer.parseInt(addr.substring(0, bits),2);
    }

    public static int isolateIndex(String addr, int tagbits, int indexbits){
        return Integer.parseInt(addr.substring(tagbits,(tagbits + indexbits)),2);
    }

    public static int isolateIndexAssociate(String addr, int blockSize, int indiceSize){
        int index = binaryToDecimal(addr);
        index = (index >>> 2/blockSize) % indiceSize;
        return index;
    }

    public static String hexToBinary(String hex){
        String binary = "";
        for (int i = 0; i < hex.length(); i++) {
            for (int j = 0 ; j < conversionInfo.size() ; j++){
                if (hex.charAt(i) == (conversionInfo.get(j).hex)){
                    binary += conversionInfo.get(j).binary;
                    break;
                }
            }
        }
        return binary;
    }

    public static void updateDirectConfig(int[] config, int configNum, int tag, int index){
        if (tag == config[index]){
            hits[configNum - 1] += 1;
        }
        else{
            config[index] = tag;
        }
    }


    public static void updateAssociate2Config(Associate2Node[] config, int configNum,
                                              int tag, int index, int lineNumber){
        /* Check if the first tag matches*/
        if (config[index].tag1 == tag){
            hits[configNum - 1] += 1;
            config[index].lastLineTag1 = lineNumber;
        }
        /* Check if the second tag matches */
        else if (config[index].tag2 == tag){
            hits[configNum - 1] += 1;
            config[index].lastLineTag2 = lineNumber;
        }
        else{
            /*Enters this if-statement if the first way is empty*/
            if(config[index].lastLineTag1 == -1){
                config[index].tag1 = tag;
                config[index].lastLineTag1 = lineNumber;
            }
            /*Enters this if-statement if the first way is full but second way is empty*/
            else if(config[index].lastLineTag2 == -1){
                config[index].tag2 = tag;
                config[index].lastLineTag2 = lineNumber;
            }
            /* Enters this else statement if replacement is needed */
            else{
                if (config[index].lastLineTag1 < config[index].lastLineTag2){
                    config[index].tag1 = tag;
                    config[index].lastLineTag1 = lineNumber;
                }
                else{
                    config[index].tag2 = tag;
                    config[index].lastLineTag2 = lineNumber;
                }
            }
        }
    }

    public static void updateAssociate4Config(Associate4Node[] config, int configNum,
                                              int tag, int index, int lineNumber){
        if (config[index].tag1 == tag){
            hits[configNum - 1] += 1;
            config[index].lastLineTag1 = lineNumber;
        }
        else if (config[index].tag2 == tag){
            hits[configNum - 1] += 1;
            config[index].lastLineTag2 = lineNumber;
        }
        else if (config[index].tag3 == tag){
            hits[configNum - 1] += 1;
            config[index].lastLineTag3 = lineNumber;
        }
        else if (config[index].tag4 == tag){
            hits[configNum - 1] += 1;
            config[index].lastLineTag4 = lineNumber;
        }
        else{
            /*Enters this if-statement if the first way is empty*/
            if(config[index].lastLineTag1 == -1){
                config[index].tag1 = tag;
                config[index].lastLineTag1 = lineNumber;
            }
            /*Enters this if-statement if the first way is full but second way is empty*/
            else if(config[index].lastLineTag2 == -1){
                config[index].tag2 = tag;
                config[index].lastLineTag2 = lineNumber;
            }
            else if(config[index].lastLineTag3 == -1){
                config[index].tag3 = tag;
                config[index].lastLineTag3 = lineNumber;
            }
            else if(config[index].lastLineTag4 == -1){
                config[index].tag4 = tag;
                config[index].lastLineTag4 = lineNumber;
            }
            /* Enters this else statement if replacement is needed */
            else{
                if (config[index].lastLineTag1 < config[index].lastLineTag2
                        && config[index].lastLineTag1 < config[index].lastLineTag3
                        && config[index].lastLineTag1 < config[index].lastLineTag4){
                    config[index].tag1 = tag;
                    config[index].lastLineTag1 = lineNumber;
                }
                else if (config[index].lastLineTag2 < config[index].lastLineTag1
                        && config[index].lastLineTag2 < config[index].lastLineTag3
                        && config[index].lastLineTag2 < config[index].lastLineTag4){
                    config[index].tag2 = tag;
                    config[index].lastLineTag2 = lineNumber;
                }
                else if (config[index].lastLineTag3 < config[index].lastLineTag1
                        && config[index].lastLineTag3 < config[index].lastLineTag2
                        && config[index].lastLineTag3 < config[index].lastLineTag4){
                    config[index].tag3 = tag;
                    config[index].lastLineTag3 = lineNumber;
                }
                else{
                    config[index].tag4 = tag;
                    config[index].lastLineTag4 = lineNumber;
                }
            }
        }
    }

    public static int binaryToDecimal(String binary){
        int num = 0;
        int value;
        for (int i = binary.length() - 1 ; i > -1 ; i--){
            value = Integer.parseInt(binary.charAt(binary.length() - i - 1) + "");

            num += value * Math.pow(2,i);
        }
        return num;
    }
}
