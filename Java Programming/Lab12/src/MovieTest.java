import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;
import java.util.Vector;

class Movie implements Serializable{
	String title, director, genres;
	int year;
	
	public Movie(String title, String director, String genres, int year) {
		this.title = title;
		this.director = director;
		this.genres = genres;
		this.year = year;
	}

	@Override
	public String toString() {
		return "[제목 : " + title + ", 감독 : " + director + ", 장르 : " + genres + ", 개봉년도 : " + year + "]";
	}	
}

public class MovieTest implements Serializable {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException{
		
		
		ObjectOutputStream oos = null;
		ObjectInputStream ois = null;
		
		Scanner input = new Scanner(System.in);
		Vector<Movie> list = new Vector<>();
		int index = 0;
		int menu;
		String search;
		
		String title, director, genres;
		int year;
	
		while(true) {
			System.out.println("====================영화 정보 관리====================");
			System.out.println("1. 입력/2. 출력/3. 검색/4. 수정/5. 삭제/6. 파일 저장/ 7. 파일 열기/ 8. 종료");
			System.out.print("메뉴 입력>> ");
			menu = input.nextInt();
			
			//1. 입력
			if(menu == 1) {
				input.nextLine();
				System.out.print("제목 : ");
				title = input.nextLine();
				System.out.print("감독 : ");
				director = input.nextLine();				
				System.out.print("장르 : ");
				genres = input.nextLine();				
				System.out.print("년도 : ");
				year = input.nextInt();
	
				list.add(new Movie(title, director, genres, year));
			}
			
			//2. 출력
			else if(menu == 2) {
				for(Movie s : list) {
					System.out.println(s.toString());
				}
			}
			
			//3. 검색
			else if(menu == 3) {
				input.nextLine();
				System.out.print("검색할 제목 입력 : ");
				search = input.nextLine();
				for(Movie s : list) {
					if(s.title.equals(search)) {
						System.out.println(s.toString());
						break;
					}
				}
			}
			
			//4. 수정
			else if(menu == 4) {
				input.nextLine();
				System.out.print("수정할 제목 입력 : ");
				search = input.nextLine();
				index = 0;
				for(Movie s : list) {
					if(s.title.equals(search)) {
						System.out.println(s.toString());
						
						System.out.print("제목 : ");
						title = input.nextLine();
						System.out.print("감독 : ");
						director = input.nextLine();				
						System.out.print("장르 : ");
						genres = input.nextLine();				
						System.out.print("년도 : ");
						year = input.nextInt();
						list.set(index,new Movie(title, director, genres, year));
						
						break;
					}
					index++;
				}
				
			}
			
			//5. 삭제
			else if(menu == 5) {
				input.nextLine();
				System.out.print("삭제할 제목 입력 : ");
				search = input.nextLine();
				index = 0;
				for(Movie s : list) {
					if(s.title.equals(search)) {
						System.out.println(s.toString());
						System.out.println("삭제되었습니다.");
						list.remove(index);
						break;
					}
					index++;
				}
			}
			
			//6. 파일 저장
			else if(menu == 6) {
				try {
					oos = new ObjectOutputStream(new FileOutputStream("movie.dat"));
					oos.writeObject(list);
					oos.flush();
					
					if(oos != null) oos.close();
					System.out.println("movie.dat에 저장되었습니다.");
					
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			
			//7. 파일 열기
			else if(menu == 7) {
				try {
					ois = new ObjectInputStream(new FileInputStream("movie.dat"));
					list = (Vector<Movie>) ois.readObject();
					
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if(ois != null) ois.close();
				}
				System.out.println("movie.dat로부터 정보를 불러왔습니다.");
			}
			
			//8. 입력
			else if(menu == 8) break;
		}			
		input.close();
	}
}
