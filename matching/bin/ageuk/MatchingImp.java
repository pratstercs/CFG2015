����   3 �  ageuk/MatchingImp  java/lang/Object name1 Ljava/lang/String; name2 <init> ()V Code
    		    	     LineNumberTable LocalVariableTable this Lageuk/MatchingImp; commonInterests .(Ljava/util/ArrayList;Ljava/util/ArrayList;I)I 	Signature V(Ljava/util/ArrayList<Ljava/lang/String;>;Ljava/util/ArrayList<Ljava/lang/String;>;I)I
    java/util/ArrayList   get (I)Ljava/lang/Object;
    ! " contains (Ljava/lang/Object;)Z
  $ % & size ()I description Ljava/util/ArrayList; 	interests found I i LocalVariableTypeTable )Ljava/util/ArrayList<Ljava/lang/String;>; StackMapTable commonInterestsList V(Ljava/util/ArrayList;Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList; �(Ljava/util/ArrayList<Ljava/lang/String;>;Ljava/util/ArrayList<Ljava/lang/String;>;Ljava/util/ArrayList<Ljava/lang/String;>;)Ljava/util/ArrayList<Ljava/lang/String;>; 4 java/lang/String
  6 7 " add listNumberMatch A(Ljava/util/ArrayList;Ljava/util/ArrayList;)Ljava/util/ArrayList; �(Ljava/util/ArrayList<Ljava/util/ArrayList<Ljava/lang/String;>;>;Ljava/util/ArrayList<Ljava/lang/String;>;)Ljava/util/ArrayList<Ljava/lang/Integer;>;
  
  =  
 ? A @ java/lang/Integer B C valueOf (I)Ljava/lang/Integer; listGens vol listNb @Ljava/util/ArrayList<Ljava/util/ArrayList<Ljava/lang/String;>;>; *Ljava/util/ArrayList<Ljava/lang/Integer;>; 	quicksort ,(Ljava/util/ArrayList;)Ljava/util/ArrayList; V(Ljava/util/ArrayList<Ljava/lang/Integer;>;)Ljava/util/ArrayList<Ljava/lang/Integer;>;
  M N O iterator ()Ljava/util/Iterator; Q S R java/util/Iterator T U next ()Ljava/lang/Object;
 ? W X & intValue Q Z [ \ hasNext ()Z
  ^ I J numbers pivot lesser greater sameAsPivot number sorted matchingList �(Ljava/util/ArrayList<Ljava/util/ArrayList<Ljava/lang/String;>;>;Ljava/util/ArrayList<Ljava/lang/String;>;)Ljava/util/ArrayList<Ljava/util/ArrayList<Ljava/lang/String;>;>;
  i 8 9
  k 0 1 
listPeople you list intlist j main ([Ljava/lang/String;)V t 1 v 2 x 3	 z | { java/lang/System } ~ out Ljava/io/PrintStream;
 � � � java/io/PrintStream � � println (Ljava/lang/Object;)V
 � � � � (I)V
  � f 9 args [Ljava/lang/String; me1 me2 test me3 
SourceFile MatchingImp.java !                     	  
   E     *� *� *� �              	                	         
   �     !>� *+� � � ��+� #����                       *    ! ' (     ! ) (    ! * +    , +  -       ! ' .     ! ) .  /    �  	 0 1      2 
   �     +>� *+� � � ,+� � 3� 5W�+� #���,�                  )     *    + ' (     + ) (    + * (   ' , +  -        + ' .     + ) .    + * .  /    �  	 8 9      : 
   �     /� Y� ;M>� ,*� � +� <� >� 5W�*� #���,�           %  &  ' " & - )    *    / D (     / E (   ' F (  
 # , +  -        / D G     / E .   ' F H  /   
 �   	 I J      K 
  �    *� #� *�*� #l<� Y� ;M� Y� ;N6*� L:� O� P � ?� V6*� � ?� V� -� >� 5W� #*� � ?� V� ,� >� 5W� �� Y ���,� ]M6� ,*� � ?� 5W����-� ]N� Y� ;:,� L:� � P � ?� V6� >� 5W� Y ���-� L:� � P � ?� V6� >� 5W� Y ����       j    -  . 
 /  0  1 ! 2 $ 3 < 4 L 5 Y 6 i 7 v 9 y 3 � ; � < � = � < � > � ? � @ � A � @ � B � C B D    f 
   _ (    � ` +   � a (  ! � b (  $ � c +  < = d +  �  , +  � ] e (  �  d +  �  d +  -   *    _ H    � a H  ! � b H  � ] e H  /   v 
� "      Q  � +     Q  �       Q  �       �        Q   	 f 9      g 
  K     t� Y� ;M*+� h� ]N6� U6� C*� � +� <-*� #dd� � ?� V� ,*� � +� Y� ;� j� 5W�*� #����*� #���,�       6    I  J  K  L  M , N > M A O L P S O Z L f K r T    >    t l (     t m (   l n (   c o (   ^ , +   L p +  -   *    t l G     t m .   l n G   c o H  /    �   � <�  	 q r  
  �     � Y� ;L� Y� ;M� Y� ;N� Y� ;:+s� 5W+u� 5W+w� 5W,s� 5W-u� 5W-w� 5W-s� 5W� Y� ;:s� 5Ww� 5W,� 5W-� 5W� 5W� y,+� Y� ;� j� � y-+� Y� ;� j� � y+� Y� ;� j� � y,+� <� �� y-+� <� �� y+� <� �� y+� �� �       ~    Y  Z  [  \ ! ] ( ^ / _ 6 ` = a D b K c R d [ e c f k g r h y i � k � l � k � m � n � m � o � p � o � q � r � s � t � v    >    � � �    � m (   � � (   � � (  ! � � (  [ � � (  -   4   � m .   � � .   � � .  ! � � G  [ � � .   �    �