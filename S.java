package a2;

public class S {
   private Strategy strategy;
   
   public S (Strategy strategy){
	   this.strategy = strategy;
   }
   
   public String execute(String s, String s2){
	   return strategy.SS(s, s2);
   }

}
