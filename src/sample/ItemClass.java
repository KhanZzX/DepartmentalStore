package sample;

import javafx.collections.ObservableList;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ItemClass {
    private Tree<Item> itemTree;
    private Item item;

    public ItemClass()
    {
        itemTree = new Tree<>();
        load();

    }

    public void insert(Item item)
    {
        itemTree.insert(item);
    }
    public Item getItemFromSerial(Item item)
    {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            Item item1 = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                String token[] = line.split(",");
                if (item.getItemBarCode().equals(token[3]))
                {
                    bufferedReader.close();
                    fileReader.close();
                    return new Item(token[0], token[1], token[2],
                            token[4],token[6],token[7],token[3],token[5]);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public double getPrice(Item item)
    {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            String token[] = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");
                if (item.getItemBarCode().equals(token[3]) || (item.getItemName().equals(token[0]) && item.getItemWeight().equals(token[4])))
                {
                    bufferedReader.close();
                    fileReader.close();
                        return Double.parseDouble(token[2]);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0.0;
    }
    public boolean isQuantityIsZeroFromFile(Item item)
    {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            String token[] = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");
                if (item.getItemName().equals(token[0]) && item.getItemWeight().equals(token[4]))
                {
                    if (token[1].equals("0")) {
                        bufferedReader.close();
                        fileReader.close();
                        return true;
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean chkItem(Item item) {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            String token[] = null;
            while ((line = bufferedReader.readLine()) != null) {
                token = line.split(",");
                if (item.getItemName().equals(token[0]) ) {
                    bufferedReader.close();
                    fileReader.close();
                    return true;
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public Item getItem(Item item)
    {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            String token[] = null;
            Item item1 = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");
                if ((item.getItemBarCode().equals(token[3]) || item.getItemName().equals(token[0])) && item.getItemWeight().equals(token[4]))
                {
                    bufferedReader.close();
                    fileReader.close();
                    return new Item(token[0], token[1], token[2],
                            token[4],token[6],token[7],token[3],token[5]);
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int size()
    {
        return itemTree.getSize();
    }
    public Item[] getAllItem()
    {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            System.out.println("size is "+itemTree.getSize());
            Item item1[] = new Item[itemTree.getSize()];
            int i = 0;
            while ((line = bufferedReader.readLine()) != null)
            {
                String token[] = line.split(",");
                    item1[i] = new Item(token[1], token[1], token[2],
                            token[4],token[6],token[7],token[3],token[5]);
                    i++;

            }

            bufferedReader.close();
            fileReader.close();
            return item1;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean chkBar(Item item) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            String token[] = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");
                if (item.getItemBarCode().equals(token[3]))
                {
                    bufferedReader.close();
                    fileReader.close();
                    return true;
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean chkWeight(Item item) {

        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            String token[] = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");
                if (item.getItemName().equals(token[0]) && item.getItemWeight().equals(token[4]))
                {
                    bufferedReader.close();
                    fileReader.close();
                        return false;
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }


    public boolean chkQuantityInFile(Item item)
    {
        FileReader fileReader = null;
        try {
            fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            String token[] = null;
            while ((line = bufferedReader.readLine()) != null)
            {
                token = line.split(",");
                if ((item.getItemBarCode().equals(token[3]) || item.getItemName().equals(token[0])) && item.getItemQuantity().equals(token[2]))
                {
                    int quant = Integer.parseInt(token[1]);
                    int quant1 = Integer.parseInt(item.getItemQuantity());
                    if (quant > quant1) {
                        bufferedReader.close();
                        fileReader.close();
                        return true;
                    }
                }
            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void saveItem(Item item)
    {
        try {
            FileWriter fileWriter = new FileWriter("Item.txt",true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(item.getItemName()+","+item.getItemQuantity()+","+item.getItemRetailPrice()
                    +","+item.getItemBarCode()+","+item.getItemWeight()+","+item.getItemBuyPrice()+","+item.getItemExpDate()
                    +","+item.getItemMfgDate()+"\n");
            bufferedWriter.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void increaseInFile(Item item)
    {
        try {
            FileReader fileReader = new FileReader("Item.txt");
            BufferedReader reader = new BufferedReader(fileReader);

            FileWriter fileWriter = new FileWriter("Temp.txt");
            BufferedWriter writer = new BufferedWriter(fileWriter);

            String line = null;
            boolean flag = true;
            while ((line = reader.readLine())!=null)
            {
                String[] token = line.split(",");
                if (token[3].equals(item.getItemBarCode()))
                {
                    itemTree.delete(item);
                    int quant = Integer.parseInt(item.getItemQuantity());
                    int quantity = quant + (Integer.parseInt(token[1]));

                    writer.write(token[0] + "," + quantity+ "," + token[2]
                            + "," + token[3] + "," + token[4] + "," + token[5]
                            + "," + token[6] +","+token[7]+ "\n");
                    itemTree.insert(new Item(token[0],token[1],token[2],token[4],token[6], token[7]
                            ,token[3],token[5]));
                    flag = false;
                }
                if (flag)
                {
                    writer.write(token[0] + "," +token[1]+ "," + token[2]
                            + "," + token[3] + "," + token[4] + "," + token[5]
                            + "," + token[6] +","+token[7]+ "\n");
                }
                flag = true;

            }

            reader.close();
            fileReader.close();

            writer.close();
            fileWriter.close();

            Files.delete(Paths.get("Item.txt"));
            File toRename = new File("Temp.txt");
            toRename.renameTo(new File("Item.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void decreaseFromFile(Item item,int quantity) {
        try {
            FileReader fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter("temp.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String line = null;
            String token[] = null;
            boolean flag = true;
            while ((line = bufferedReader.readLine()) != null) {
                token = line.split(",");
                if (( item.getItemBarCode().equals(token[3]))) {

                    itemTree.delete(item);
                    int quant = (Integer.parseInt(token[1])) - quantity;
                    System.out.println("quant is "+quant);
                    if (quant != 0) {
                        bufferedWriter.write(token[0] + "," + quant+ "," + token[2]
                                + "," + token[3] + "," + token[4] + "," + token[5]
                                + "," + token[6] +","+token[7]+ "\n");
                        itemTree.insert(new Item(token[0],token[1],token[2],token[4],token[6], token[7]
                                ,token[3],token[5]));
                        flag = false;
                    }
                }
                if (flag) {
                    bufferedWriter.write(token[0] + "," + token[1]+ "," + token[2]
                            + "," + token[3] + "," + token[4] + "," + token[5]
                            + "," + token[6] +","+token[7]+ "\n");
                }
                flag = true;
            }

            bufferedWriter.close();
            fileWriter.close();
            bufferedReader.close();
            fileReader.close();

            Files.delete(Paths.get("Item.txt"));

            File toRename =  new File("temp.txt");
            toRename.renameTo(new File("Item.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        public void load()
       {
        try {
            FileReader fileReader = new FileReader("Item.txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            Item item =  null;
            while ((line = bufferedReader.readLine()) != null)
            {
                String token[] = line.split(",");
                itemTree.insert(new Item(token[0],token[1],token[2],token[4],token[6], token[7]
                        ,token[3],token[5]));

            }
            bufferedReader.close();
            fileReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {

    }
}
