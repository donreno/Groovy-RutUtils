package com.donreno.rututils


class CircularCollectionIterator {
	
	def array
	def currentIndex
	
	CircularCollectionIterator(def array){
		currentIndex = 0
		this.array = array
	}
	
	def current(){
		return array[currentIndex]
	}
	
	def prev(){
		lowerCircularOffset()
		
		currentIndex--
		
		return current()
	}
	
	def next(){
		upperCircularOffset()
		
		currentIndex++
		
		return current()
	}

	private upperCircularOffset() {
		if(currentIndex == (array.size() - 1)){
			currentIndex = -1
		}
	}
	
	private lowerCircularOffset() {
		if(currentIndex==0){
			currentIndex = array.size()
		}
	}

}
