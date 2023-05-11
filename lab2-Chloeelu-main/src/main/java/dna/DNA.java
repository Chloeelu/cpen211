package dna;

import java.util.*;
import java.lang.String;


//main class
public class DNA{
    String seq;//create a DNA sequence;
    double Amass=135.128;
    double Cmass=111.103;
    double Gmass=151.128;
    double Tmass=125.107;
    double Junkmass=100.000;

    static boolean valid(String seq){
        return clearjunk(seq).length()%3==0;
    }
    //create a new seq without junk part;
    static String clearjunk(String seq){
        String nojunkseq="";
        for(int i=0;i<seq.length();i++){
            if(seq.charAt(i)=='A'||seq.charAt(i)=='C'||seq.charAt(i)=='T'||seq.charAt(i)=='G'){
                nojunkseq+=seq.charAt(i);
            }
        }
        return nojunkseq;
    }

    //test1: create a dnaString of nucleotide
    public DNA(String seq){
        // check if dna sequence is valid or not;
        if(!valid(seq)) {
            throw new IllegalArgumentException("Invalid DNA sequence");
        }
        else{
            this.seq=seq;
        }
    }
    //test2: check if the dna sequence is a protein
    public boolean isProtein() {
        String ATG = "ATG";
        String TAA = "TAA";
        String TAG = "TAG";
        String TGA = "TGA";
        String start = seq.substring(0, 3);
        String end = seq.substring(seq.length() - 4);
        String nojunk = clearjunk(seq);
        int countC = 0, countG = 0;
        if (start.equals(ATG)) {
            return true;
        }//check if the sequence starts from ATG;
        if (end.equals(TAA) ||end.equals(TAG) ||end.equals(TGA)) {
            return true;
        }//check if the sequence ends with TAA or TAG or TGA;
        if (nojunk.length() >= 15) {
            return true;
        }
        //check if the sequence has ast least five codons;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == 'C') {
                countC += 1;
            }
            if (seq.charAt(i) == 'G') {
                countG += 1;
            }
        }
        double totalmass = totalMass();
        double portion = (countC * Cmass + countG * Gmass) / totalmass;
        return !(portion < 0.3);
    }

    //test3:get the total mass rounded to one decimal place;
    public double totalMass(){
        int countA=0,countC=0,countG=0,countT=0,countJunk;
        double totalmass;//mass wiithout rounded
        double totalMass;
        String nojunk=clearjunk(seq);
        for(int i=0;i<seq.length();i++){
            if(seq.charAt(i) == 'A'){
                countA+=1;
            }
            else if(seq.charAt(i) == 'C'){
                countC+=1;
            }
            else if(seq.charAt(i) == 'G'){
                countG+=1;
            }
            else if(seq.charAt(i) == 'T'){
                countT+=1;
            }
        }
        countJunk=seq.length()-nojunk.length();//the number of each part appears in dna sequence;
        totalmass=countA*Amass+countC*Cmass+countG*Gmass+countT*Tmass+countJunk*Junkmass;
        totalMass=Math.round(totalmass*10.0)/10.0;
        return totalMass;
    }
    //test4:count of a nuc in dna sequence;
    public int nucleotideCount(char c) {
        int count=0;
        String nojunk=clearjunk(seq);
        for(int i=0;i<nojunk.length();i++){
            if(nojunk.charAt(i) == c){
                count++;
            }
        }
        return count;
    }

    //test4:return a set that contains all the distinct codons in the DNA sequence
    public Set<String> codonSet(){
        String nojunk=clearjunk(seq);
        String codon;
        Set<String> distinctcodon= new HashSet<>();//create a set with distinct codon;
        int b=0;//number of codons
        for(int a=0;a<(nojunk.length())/3;a++){
            codon=nojunk.substring(b,b+3);
            distinctcodon.add(codon);
            b+=3;
        }
        return distinctcodon;
    }
    //test5: Alters the DNA sequence by replacing all occurrences of originalCodon with newCodon, and eliminates all junk regions;
    public void mutateCodon(String originalCodon, String newCodon){
        String nojunk=clearjunk(seq);
        if(valid(originalCodon)&&valid(newCodon)&&nojunk.contains(originalCodon)) {
            this.seq = "";
            ArrayList<String> mutate = new ArrayList<>();
            for (int i = 0; i < nojunk.length(); i += 3) {
                if (!(nojunk.substring(i, i + 3)).equals(originalCodon)) {
                    mutate.add(nojunk.substring(i, i + 3));
                } else {
                    mutate.add(newCodon);
                }
            }
            for (String s : mutate) {
                this.seq += s;
            }
        }
    }

    //test6:  return the nucleotide sequence;
    public String sequence(){
        return this.seq;
    }

}