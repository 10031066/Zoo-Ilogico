package com.itc.machozoo2;

import android.graphics.Rect;
import android.util.Log;

public class ActulizandoAnimales extends Thread{
	Sprite Animal;
	private boolean atacando;
	ActulizandoAnimales(Sprite Animal){
		this.Animal= Animal;
		
		
		start();
	}
	
	@Override
	public void run(){
	  while(true){
		
		int srcX = Animal.currentFrame * Animal.width;
		int srcY = Animal.getAnimationRow() * Animal.height;
	
		Animal.src = new Rect(srcX, srcY, srcX + Animal.width, srcY + Animal.height);
		Animal.dst = new Rect(Animal.x, Animal.y, Animal.x + Animal.width, Animal.y + Animal.height);
		//System.out.println(Figuras.size());
		for (Figura f : Animal.Figuras) {
			
			if( Animal.id != f.get_id()){// && this.get_tipo() != f.get_tipo()){
				//System.out.println(f.id);
				if(Animal.atacar){
					Animal.buscaRejaAtacar();
					if(Animal.victima!=null){
						atacando=true;
						try {
							Animal.atacandoJaula();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}else
			if (Rect.intersects(Animal.dst, f.dst) ) {
				//System.out.println("Se interceptaron");
				
				
				
				Animal.flag = false;
				
				Animal.v=!Animal.v;
				Animal.h=!Animal.h;
				break;
			} else {
				Animal.flag = true;
			}
			}
		}
		update();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  }
	}
	
	void update() {
		if(!Animal.comiendo()&&!atacando){
			if (Animal.x > Animal.gameView.getWidth() - 133 ) {
				Animal.xSpeed = -10;
			}
			if (Animal.x + Animal.xSpeed < 250 ) {
				Animal.xSpeed = 10;
			}
			if(!Animal.flag){ //Log.i("zoo", "hola");
				if(Animal.v){
					Animal.ySpeed=Animal.ySpeed*(-1);
				}if(Animal.h){
					Animal.xSpeed=Animal.xSpeed*(-1);
				}
			
			}
			Animal.x = Animal.x + Animal.xSpeed;
			if (Animal.y > Animal.gameView.getHeight() - Animal.height - Animal.ySpeed || Animal.y + Animal.ySpeed < 0) {
				Animal.ySpeed = -Animal.ySpeed;
			}
			Animal.y = Animal.y + Animal.ySpeed;
			Animal.currentFrame = ++Animal.currentFrame % Animal.BMP_COLUMNS;
		}
	}
}
