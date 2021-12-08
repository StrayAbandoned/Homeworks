package ru.geekbrains.lesson2_1;

public class Wall implements Challenge{
    private int parametr;
    Wall(int parametr){
        this.parametr = parametr;
    }

    @Override
    public boolean overcome(Participants a, int parametr) {
        if (a.jump(parametr)){
            return true;
        }
        else return false;
    }

    @Override
    public int getParametr (){
        return parametr;
    }
}
