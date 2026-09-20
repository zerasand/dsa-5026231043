package lw01.prelab;

public class ColourPrint extends PrintJob {

        ColourPrint(String id, int pages){
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = super.getPages();
        if (pages > 10){
            pages -= 10;
            return (10 * 1500) + (pages * 1000) + 2000;
        } else {
            return pages * 1500 + 2000;
        }
    }

    @Override
    String label() {
        return "Colour";
    }
    
}
