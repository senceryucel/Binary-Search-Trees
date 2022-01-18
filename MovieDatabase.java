//Sencer Yücel
//MovieDatabase Class for hw3

public class MovieDatabase{
    BST movieTree = new BST();
    public void addMovie(String movieTitle, String directorFirstName, String directorLastName, int releaseDay, int releaseMonth, int releaseYear) {
        String s = movieTitle+","+releaseYear +","+ directorFirstName+" "+directorLastName+","+releaseDay+","+releaseMonth;
        double key = (double)releaseYear+ (releaseMonth/1.1);
        if(movieTree.contains(key)) {
            System.out.println("ERROR: Movie "+movieTitle+" overwritten");
        }
        else {
            System.out.println("INFO: Movie "+movieTitle+" has been added");
            movieTree.put(key,s);
        }
    }
    public void removeMovie(String movieTitle) {
        if((movieTree.getKey(movieTitle)) != null) {
            double key = (double)movieTree.getKey(movieTitle);
            movieTree.delete(key);
            System.out.println("INFO: Movie "+movieTitle+" has been removed.");
        }
        else {
            System.out.println("Warning there is no " + movieTitle + " in the database.");
        }
    }
    //With the put method from BST, we insert actors to movies. For every movie, there is another tree for actors as being asked in the PDF.
    public void addActor(String movieTitle, String actorFirstName, String actorLastName, String actorRole) {
        if(movieTree.getKey(movieTitle) != null) {
            double key = (double)movieTree.getKey(movieTitle);
            if(movieTree.getBST(key).get(actorFirstName+actorLastName) == null) {
                movieTree.getBST(key).put(actorFirstName + actorLastName, actorFirstName + " " + actorLastName + "," + actorRole);
                System.out.println("INFO: " +actorFirstName + " " + actorLastName + " has been added to the movie " + movieTitle);
            }
            else{
                System.out.println("The actor"+actorFirstName+" "+actorLastName+" is already have role in "+movieTitle);
            }
        }
        else {
            System.out.println("Movie "+movieTitle+" is not exist");
        }
    }
    //Removes an actor from a specific movie.
    public void removeActor(String movieTitle, String actorFirstName, String actorLastName) {
        if((movieTree.getKey(movieTitle)) != null) {
            double key = (double)movieTree.getKey(movieTitle);
            if(movieTree.getBST(key).get(actorFirstName+actorLastName) != null) {
                movieTree.getBST(key).delete(actorFirstName + actorLastName);
                System.out.println("INFO:" + actorFirstName + " " + actorLastName + " has been removed from " + movieTitle);
            }
            else {
                System.out.println("There is no name called "+actorFirstName+" "+actorLastName+" in the "+movieTitle);
            }
        }
    }
    //This method shows all movies according to the pdf itself. My "Values" method in BST class has been created for this.
    public void showAllMovies() {
        Queue<String[]> q = movieTree.Values();
        if(q.isEmpty()) {
            System.out.println("--none--");
        }
        for(int i = 0; i<q.size(); i++) {
            String arr[] = q.dequeue();
            System.out.println(arr[0]+", "+arr[1]+", "+arr[2]);
        }
    }
    //Shows a specific movie with the parameter movie title. I used queue structure to do so.
    public void showMovie(String movieTitle) {
        if((movieTree.getKey(movieTitle)) != null) {
            double key = (double)movieTree.getKey(movieTitle);
            String array[] = (String[])movieTree.get(key);
            System.out.println(array[0]+"\n"+""+array[3]+"/"+array[4]+"/"+array[1]);
            System.out.println(array[2]);
            Queue<String[]> q = movieTree.getBST(key).Values();
            if(q.isEmpty()) {
                System.out.println("--none--");
            }
            int length = q.size();
            for(int i = 0; i<length; i++) {
                String[] arr2 = q.dequeue();
                System.out.println(arr2[0]+", "+arr2[1]);
            }
        }
        else {
            System.out.println(movieTitle+ "\n--none--");
        }
    }

    //This method shows the actor roles in reversed order with using the Stack structure. I used a little complicated algorithm but it works.
    public void showActorRoles(String actorFirstName, String actorLastName) {
        Stack<String[]> stack = movieTree.ReverseValues();
        int a = 0;
        int counter = 0;
        while(!stack.isEmpty()) {
            String[] arr = stack.pop();
            double key = (double)movieTree.getKey(arr[0]);
            BST bst = movieTree.getBST(key);
            Stack<String[]> actorStack = bst.ReverseValues();
            while (!actorStack.isEmpty()) {
                String[] actorArr = actorStack.pop();
                if(actorArr[0].equals(actorFirstName+" "+actorLastName)) {
                    if(a == 0) {
                        System.out.println(actorArr[0]);
                        a++;
                    }
                    counter++;
                    System.out.println(actorArr[1]+", "+arr[0]+", "+arr[1]);
                }
            }
        }
        if(counter == 0) {
            System.out.println(actorFirstName+" "+actorLastName);
            System.out.println("--none--");
        }
    }
    //Having a director's first and last name, this method finds all of the movies that director has. We needed to do it in reverse order again, so I used Stack structure again.
    public void showDirectorMovies(String directorFirstName, String directorLastName) {
        Stack<String[]> stack = movieTree.ReverseValues();
        int a = 0;
        int counter = 0;
        while(!stack.isEmpty()) {
            String[] arr = stack.pop();
            if (arr[2].equals(directorFirstName + " " + directorLastName)) {
                if (a == 0) {
                    System.out.println(arr[2]);
                    a++;
                }
                counter++;
                System.out.println(arr[0] + ", " + arr[3] + "/" + arr[4] + "/" + arr[1]);
            }
        }
        if(counter == 0) {
            System.out.println(directorFirstName+" "+directorLastName);
            System.out.println("--none--");
        }
    }
}
