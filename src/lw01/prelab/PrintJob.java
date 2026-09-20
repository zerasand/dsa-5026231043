package lw01.prelab;

public abstract class PrintJob implements Chargeable {

    private String id;
    private int pages;

    PrintJob(String id, int pages){

        if (pages <= 0){
            throw new IllegalArgumentException("You can't print 0 page!");
        }

        this.id = id;
        this.pages = pages;
    }

    String getId(){
        return this.id;
    }

    int getPages(){
        return this.pages;
    }
    
    @Override
    public abstract int calculateCharge();

    public int calculateCharge(int copies){

        if (copies <= 0){
            throw new IllegalArgumentException("You can't print 0 page!");
        }

        return calculateCharge() * copies;
    }

    String label(){
        return "Print";
    }

    String summary(){
        return this.id + " | " + label() + " | " + calculateCharge();
    }




}
