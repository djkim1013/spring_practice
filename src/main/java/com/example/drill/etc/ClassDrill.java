package com.example.drill.etc;

class ClassDrillExtended extends  ClassDrillSuper{
    ClassDrillExtended(){
        super();
        gen = 1;
    }
}

class ClassDrillSuper{
    int gen = 0;
    void incGen(){
        gen++;
    }
    void incInnerGen(){
        this.innerClass.gen++;
    }

    InnerClass innerClass = new InnerClass();
    class InnerClass{
        int gen = 1;
        void incGen(){
            ++gen;
        }
        void incSuperGen(){
            ++ClassDrillSuper.this.gen;
        }
    }

}

public class ClassDrill{
    public static void main(String[] args) {
        ClassDrillSuper classDrillSuper = new ClassDrillSuper();
        System.out.println(classDrillSuper.gen+" "+classDrillSuper.innerClass.gen);
        classDrillSuper.incGen();
        System.out.println(classDrillSuper.gen+" "+classDrillSuper.innerClass.gen);
        classDrillSuper.incInnerGen();
        System.out.println(classDrillSuper.gen+" "+classDrillSuper.innerClass.gen);
        classDrillSuper.innerClass.incGen();
        System.out.println(classDrillSuper.gen+" "+classDrillSuper.innerClass.gen);
        classDrillSuper.innerClass.incSuperGen();
        System.out.println(classDrillSuper.gen+" "+classDrillSuper.innerClass.gen);
        ClassDrillExtended classDrillExtended = new ClassDrillExtended();
        System.out.println(classDrillExtended.gen+" "+classDrillExtended.innerClass.gen);
        classDrillExtended.incInnerGen();
        System.out.println(classDrillExtended.gen+" "+classDrillExtended.innerClass.gen);

    }
}
