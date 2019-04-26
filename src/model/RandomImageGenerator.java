package model;

import data.IRandomShow;
import javafx.scene.image.Image;

import java.util.Random;

public class RandomImageGenerator implements IRandomShow {

    private String[] adresses={"/res/drawable/r1.jpg", "/res/drawable/r2.jpg", "/res/drawable/r3.jpg", "/res/drawable/r4.jpg",
            "/res/drawable/r5.jpeg", "/res/drawable/r6.jpg", "/res/drawable/r7.jpg", "/res/drawable/r8.jpg",
            "/res/drawable/r9.jpg", "/res/drawable/r10.jpg"};

    private Image[] images;

    private static RandomImageGenerator instance=null;

    private RandomImageGenerator(){
        images=new Image[10];
        for(int i=0; i<adresses.length; i++){
            images[i]=new Image(adresses[i]);
        }
    }

    public static RandomImageGenerator getInstance() {
        if (instance==null){
            instance=new RandomImageGenerator();
        }
        return instance;
    }

    @Override
    public Image getImage() {
        Random rand=new Random();
        return images[rand.nextInt(10)];
    }
}
