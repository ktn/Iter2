private static class Grid<T> extends AbstractList<T>{
	private final int childNum = 4; //Change this to increase board size
	private final T[] members = new T[childNum];

	/*========================================
		The standard is as follows
		1st child = right child
		2nd child = bottom child
		3rd child = left child
		4th child = top child
	=========================================*/

	public T get(int index) {
        return members[index];
    }

    public T set(int index, T element) {
    	//check for out of bounds, return null if true
    	if(index >= childNum)
    		return NULL

        T oldValue = members[index];
        members[index] = element;
        return oldValue;
    }

    public int size() {
        return members.length;
    }

    public int indexOf(T element){
    	//behaves like indexOf in ArrayList
    	int ret = -1;

    	for(int i = 0; i < childNum; i++){
    		if(members[i] == T)
    			ret = i;
    	}

    	return ret;
    }

    public void add(T element){
    	//behaves like add in ArrayList

    	for(int i = 0; i < childNum; i++){
    		if(members[i] != NULL)
    		{
    			members[i] = T;
    			return;
    		}	
    	}
    }

    public void remove(T element){
    	//behaves like remove in ArrayList

    	for(int i = 0; i < childNum; i++){
    		if(members[i] == T)
    			members[i] = NULL;
    	}
    }

    public void rotate(){

    	T[] temp = Arrays.copyOf(members);

    	for(int i = 0; i < childNum; i++){
    		if(i != childNum - 1){
 				members[i] = temp[i+1];
    		}
    		else{
    			members[i] = temp[0];
    		}
    			
    		
    	}
    }
}