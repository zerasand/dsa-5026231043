package lw01.prelab;

public class MonoPrint extends PrintJob{

    MonoPrint(String id, int pages){
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        return super.getPages() * 500;
    }

    @Override
    String label() {
        return "Mono";
    }
}
