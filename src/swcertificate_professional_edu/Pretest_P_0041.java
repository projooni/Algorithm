package swcertificate_professional_edu;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 기출 P-0041 K-Heap 슈퍼이벤트
 * 
 * */
public class Pretest_P_0041 {
	
	public static int Q;
	
	public static class Node {
	    private int data;
	    private Node parent;
	    private Node left;
	    private Node right;    
	    
	    public Node(int data){
	        this.setData(data);
	        setLeft(null);
	        setRight(null);
	    }
	 
	    public int getData() {return data;}
	    public void setData(int data) {this.data = data;}
	    public Node getLeft() {return left;}
	    public void setLeft(Node left) {this.left = left;}
	    public Node getRight() {return right;}
	    public void setRight(Node right) {this.right = right;}
	    public Node getParent() {return parent;}
	    public void setParent(Node parent) {this.parent = parent;}
	}
	
	public static class BinarySearchTree {
	    public Node root;
	    public int cnt;
	    public int num;
	    public BinarySearchTree(){
	        this.root = null;
	    }
	    //탐색 연산
	    public int find(int k){
	        Node current = root;
	        cnt = 0;
	        num = 0;
	        InOrderPrintTree(current, k);
	        return num;
	        
	        
//	        while(root.left != null) {
//	        	current.parent = current;
//	        	current = current.left;
//	        }
//	        
//	        while(current!=null){
//	        	
//	            if(cnt == id){
//	                return true;
//	            } else{
//	            	if(current.getParent().getLeft() == current) {
//	            		current = current.getParent();
//	            	}else {
//	            		current = current.getParent().getRight();
//	            	}
//	            	cnt++;
//	            }
//	            
//	        }
//	        return false;
	    }
	    
	    // 중위 순회
	    void InOrderPrintTree(Node node, int k) {
	    	if(node == null || k == cnt) {
	    		return;
	    	}
	    	
	    	InOrderPrintTree(node.getLeft(), k);
	    	cnt++;
	    	if(k == cnt) {
	    		num = node.getData();
	    		return;
	    	}
	    	InOrderPrintTree(node.getRight(), k);
	    	
	    }
	    
	  //삽입 연산
	    public void insert(int id){
	        Node newNode = new Node(id);
	        if(root==null){
	            root = newNode;
	            return;
	        }
	        Node current = root;
	        Node parent = null;
	        while(true){
	            parent = current;
	            if(id < current.getData()){                
	                current = current.getLeft();
	                if(current==null){
	                    parent.setLeft(newNode);
	                    return;
	                }
	            }else{
	                current = current.getRight();
	                if(current==null){
	                    parent.setRight(newNode);
	                    return;
	                }
	            }
	        }
	    }
	    
	  //삭제 연산
	    public boolean delete(int id){
	        Node parent = root;
	        Node current = root;
	        boolean isLeftChild = false;
	        
	        int cnt = 0;
	        while(cnt != id){
	            parent = current;
	            if(current.getData()>id){
	                isLeftChild = true;
	                current = current.getLeft();
	                cnt++;
	            }else{
	                isLeftChild = false;
	                current = current.getRight();
	                cnt++;
	            }
	            if(current==null){
	                return false;
	            }
	        }
	        //Case 1: 자식노드가 없는 경우
	        if(current.getLeft()==null && current.getRight()==null){
	            if(current==root){
	                root = null;
	            }
	            if(isLeftChild==true){
	                parent.setLeft(null);
	            }else{
	                parent.setRight(null);
	            }
	        }
	        
	      //Case 2 : 하나의 자식을 갖는 경우
	        else if(current.getRight()==null){
	            if(current==root){
	                root = current.getLeft();
	            }else if(isLeftChild){
	                parent.setLeft(current.getLeft());
	            }else{
	                parent.setRight(current.getLeft());
	            }
	        } else if(current.getLeft()==null){
	            if(current==root){
	                root = current.getRight();
	            }else if(isLeftChild){
	                parent.setLeft(current.getRight());
	            }else{
	                parent.setRight(current.getRight());
	            }
	        }
	        
	      //Case 3 : 두개의 자식을 갖는 경우
	        else if(current.getLeft()!=null && current.getRight()!=null){
	            // 오른쪽 서브트리의 최소값을 찾음
	            Node successor = getSuccessor(current);
	            if(current==root){
	                root = successor;
	            }else if(isLeftChild){
	                parent.setLeft(successor);
	            }else{
	                parent.setRight(successor);
	            }            
	            successor.setLeft(current.getLeft());
	        }        
	        return true;        
	    }
	    
	    public Node getSuccessor(Node deleleNode){
	        Node successsor =null;
	        Node successsorParent =null;
	        Node current = deleleNode.getRight();
	        while(current!=null){
	            successsorParent = successsor;
	            successsor = current;
	            current = current.getLeft();
	        }
	        if(successsor!=deleleNode.getRight()){
	            successsorParent.setLeft(successsor.getRight());
	            successsor.setRight(deleleNode.getRight());
	        }
	        return successsor;
	    }
		
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		
		System.setIn(new FileInputStream("D://sample_input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc=1; tc<=T; tc++) {
			
			Q = Integer.parseInt(br.readLine().trim());
			
			BinarySearchTree bst = new BinarySearchTree();
			
			List<Integer> resultList = new ArrayList<Integer>();
			
			for(int i=0; i<Q; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int type = Integer.parseInt(st.nextToken());
				
				if(type == 1) {
					// 	카드를 뽑는다.
					int card = Integer.parseInt(st.nextToken());
					bst.insert(card);
				}else {
					// 진행자가 당첨자를 선언한다.
					int K = Integer.parseInt(st.nextToken());
					int num = bst.find(K);
					resultList.add(num);
					bst.delete(num);
				}
				
			}
			
			bw.flush();
			bw.write("#" + tc);
			for(int i=0; i<resultList.size(); i++) {
				bw.write(" " + resultList.get(i));
			}
			bw.write("\n");
		}
		bw.close();

	}

}
