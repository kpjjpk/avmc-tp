// This is mutant program.
// Author : ysma

package ar.edu.itba.stryker;


import roops.core.objects.SinglyLinkedListNode;



public class SinglyLinkedList
{

	/*@
    @ invariant (\forall SinglyLinkedListNode n; \reach(header, SinglyLinkedListNode, next).has(n); \reach(n.next, SinglyLinkedListNode, next).has(n)==false);
    @*/
	public /*@nullable@*/ roops.core.objects.SinglyLinkedListNode header;

	public SinglyLinkedList()
	{
	}

	//----------------- showInstance --------------------//
	/*@ requires \reach(this.header, SinglyLinkedListNode, next).int_size() == 100;    
    @ ensures \result == false;
    @*/
	public boolean showInstance()
	{
		return true;
	}

	/*@
    @ ensures \result == true;
    @ signals (Exception e) false;
    @*/
	public boolean contains(  /*@nullable@*/ java.lang.Object value_param )
	{
		roops.core.objects.SinglyLinkedListNode current;
		current = this.header; //mutGenLimit 1
		current.next = current.next.next; //mutGenLimit 1
		return true;
	}

	//--------------------------- getNode ----------------------------//    
	/*@
    @ requires index>=0 && index<\reach(this.header, SinglyLinkedListNode, next).int_size();
    @ ensures \reach(this.header, SinglyLinkedListNode, next).has(\result)==true; 
    @ ensures \reach(\result, SinglyLinkedListNode, next).int_size() == \reach(this.header, SinglyLinkedListNode, next).int_size()-index;
    @ signals (Exception e) false;
    @*/
	public roops.core.objects.SinglyLinkedListNode getNode( int index )
	{
		roops.core.objects.SinglyLinkedListNode current = header;
		roops.core.objects.SinglyLinkedListNode result = null;
		int current_index = 0;
		while (result == null && current != null) {
			if (index == current_index) {
				result = current;
			}
			current_index = current_index + 1;
			current = current.next;
		}
		return result;
	}

	//------------------------ insertBack --------------------------//    
	//Due to jml4c the ensures clauses must be in that order :(      
	/*@
    @ requires freshNode!=null;
    @ requires \reach(header, SinglyLinkedListNode, next).has(freshNode)==false; 
    @ ensures \reach(header, SinglyLinkedListNode, next).int_size()==\old(\reach(header, SinglyLinkedListNode, next)).int_size()+1;
    @ ensures (\forall SinglyLinkedListNode n; 
    @            \old(\reach(header, SinglyLinkedListNode, next)).has(n);
    @			 \reach(header, SinglyLinkedListNode, next).has(n)==true  
    @         );
    @ ensures (\exists SinglyLinkedListNode n; 
    @            \reach(header, SinglyLinkedListNode, next).has(n); 
    @            n.next==null && n.value==data);
    @ signals (Exception e) false;
    @*/
	void insertBack( java.lang.Object data, roops.core.objects.SinglyLinkedListNode freshNode )
	{
		freshNode.value = data;
		freshNode.next = null;
		if (this.header == null) {
			this.header = freshNode;
		} else {
			roops.core.objects.SinglyLinkedListNode current = this.header;
			while (current.next != null) {
				current = current.next;
			}
			current.next = freshNode;
		}
	}

	public static void main(String[] args) {
		SinglyLinkedList list = new SinglyLinkedList();
		list.header = new SinglyLinkedListNode();

		list.contains(null);
	}

}
