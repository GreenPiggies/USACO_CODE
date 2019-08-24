import java.io.*;
import java.util.*;
/*
ID: hungwes1
LANG: JAVA
TASK: castle
*/
public class castle 
{
	static int rows = 0;
	static int columns = 0;
	static class Wall
	{
		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			result = (int) (prime * result + x);
			result = (int) (prime * result + y);
			return result;
		}
		@Override
		public boolean equals(Object obj) 
		{
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Wall other = (Wall) obj;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}
		double x;
		double y;
		public Wall(double x, double y)
		{
			this.x = x;
			this.y = y;
		}
		
		public boolean hasMorePriorityThan(Wall that) 
		{
			if (this.y < that.y)
			{
				return true;
			} else if (that.y < this.y)
			{
				return false;
			} else
			{
				if (this.x > that.x)
				{
					return true;
				} else
				{
					return false;
				}
			}
		}
		
		public String toString()
		{
			return ("<" + x + ", " + y + ">");
		}
		
		
		
		
	}
	static class Module
	{
		public boolean isNextTo(Module that)
		{
			if ((this.x == that.x && Math.abs(that.y - this.y) == 1) 
				|| (this.y == that.y && Math.abs(that.x - this.x) == 1))
			{
				return true;
			}
			return false;
		}
		
		@Override
		public int hashCode() 
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + moduleNum;
			result = prime * result + ((neighbors == null) ? 0 : neighbors.hashCode());
			result = prime * result + (visited ? 1231 : 1237);
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}

		@Override
		public boolean equals(Object obj) 
		{
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Module other = (Module) obj;
			if (moduleNum != other.moduleNum)
				return false;
			if (neighbors == null) {
				if (other.neighbors != null)
					return false;
			} else if (!neighbors.equals(other.neighbors))
				return false;
			if (visited != other.visited)
				return false;
			if (x != other.x)
				return false;
			if (y != other.y)
				return false;
			return true;
		}

		int moduleNum;
		int x;
		int y;
		boolean visited;
		/*boolean westWall;
		boolean northWall;
		boolean eastWall;
		boolean southWall;
		*/
				
		List<Module> neighbors;
		
		public Module(int moduleNum, int x, int y)
		{
			this.moduleNum = moduleNum;
			this.x = x;
			this.y = y;
			neighbors = new ArrayList<Module>();
			visited = false;
		}
		
		public void addNeighbors(Module[][] modules, int info)
		{
			int b1 = 0b0001;
			int b2 = 0b0010;
			int b4 = 0b0100;
			int b8 = 0b1000;
			
			if ((info & b1) == 0)
			{
				// westWall = true;
				neighbors.add(modules[x][y - 1]);
			}
			if ((info & b2) == 0)
			{
				// northWall = true;
				neighbors.add(modules[x - 1][y]);
			}
			if ((info & b4) == 0)
			{
				// eastWall = true;
				neighbors.add(modules[x][y + 1]);
			}
			if ((info & b8) == 0)
			{
				// southWall = true;
				neighbors.add(modules[x + 1][y]);
			}
			
		}
		
		public String toString()
		{
			return String.valueOf(moduleNum);
		}
		
		
	}
	
	final static int B3 = 0b10;
	
	public static void main(String[] args) throws Exception
	{
		// Set up the reader and writer
		BufferedReader f = new BufferedReader(new FileReader("castle.in"));	    
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));
	    
	    // Read in stuff
	    // Read in castle size
		String[] sizes = f.readLine().split(" ");
		rows = Integer.parseInt(sizes[1]);
		columns = Integer.parseInt(sizes[0]);
		
		// Create the matrices
		
		int[][] rooms = new int[rows][columns];	// Do I even need this one?
		
		Module[][] modules = new Module[rows][columns]; // This is to store modules
		
		int counter = 1;
		
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				modules[i][j] = new Module(counter, i, j);
				counter++;
			}
		}
		
		
		for (int i = 0; i < rows; i++)
		{
			String[] info = f.readLine().split(" ");
			for (int j = 0; j < columns; j++)
			{
				rooms[i][j] = Integer.parseInt(info[j]);
				modules[i][j].addNeighbors(modules, rooms[i][j]);
			}
		}
		
		
		List<List<Module>> components = getComponents(modules);
		System.out.println(components);
		out.println(components.size());
		
		Wall wall = getBiggestRoom(components, out);
		
		out.println(wallToString(wall));
		
		System.out.println(wall);
		
		out.close();
						
	}
	
	private static int biggest(Set<Integer> nums)
	{
		int max = Integer.MIN_VALUE;
		for (Integer num : nums)
		{
			if (num > max)
			{
				max = num;
			}
		}
		return max;
	}
	
	private static String wallToString(Wall wall)
	{
		String str = "";
		int wallX = 0;
		int wallY = 0;
		String letter = "";
		if (Math.round(wall.x) == wall.x)
		{
			// This means it's a vertical wall, Let's use E!
			wallX = (int) (wall.x + 1);
			wallY = (int) (wall.y + 0.5);
			letter = "E";
		} else
		{
			// This means it's a horizontal wall, Let's use N!
			wallX = (int) (wall.x + 1.5);
			wallY = (int) (wall.y + 1);
			letter = "N";
		}
		str = wallX + " " + wallY + " " + letter;
		return str;
		
	}
	
	private static Wall getBiggestRoom(List<List<Module>> components, PrintWriter out) 
	{
		Map<Integer, Wall> connections = new HashMap<>();
		int biggestComponent = components.get(0).size();
		for (int index = 1; index < components.size(); index++)
		{
			if (components.get(index).size() > biggestComponent)
			{
				biggestComponent = components.get(index).size();
			}
		}
		out.println(biggestComponent);
		for (int i = 0; i < components.size(); i++)
		{
			for (int j = i + 1; j < components.size(); j++)
			{
				List<Module> room1 = components.get(i);
				List<Module> room2 = components.get(j);
				
				Wall wall = getWall(room1, room2);
				
				if (wall != null)
				{
					int size = room1.size() + room2.size();
					
					if (connections.containsKey(size))
					{
						Wall other = connections.get(size);
						
						if (other != null && wall.hasMorePriorityThan(other))
						{
							connections.replace(size, wall);
						}
					} else
					{
						if (wall != null)
						{
							connections.put(size, wall);
						}
					}
				}
			}
		}
		int biggest = biggest(connections.keySet());
		out.println(biggest);
		return connections.get(biggest(connections.keySet()));
		
	}
	
	private static Wall getTheWall(List<Wall> walls)
	{
		Wall theWall = walls.get(0);
		for (int i = 1; i < walls.size(); i++)
		{
			Wall newWall = walls.get(i);
			if (newWall.hasMorePriorityThan(theWall))
			{
				theWall = newWall;
			}
		}
		return theWall;
	}

	private static Wall getWall(List<Module> room1, List<Module> room2) 
	{
		List<Wall> walls = new ArrayList<Wall>();
		for (Module mod1 : room1)
		{
			for (Module mod2 : room2)
			{
				if (mod1.isNextTo(mod2))
				{
					double wallX = (mod1.x + mod2.x) / 2.0;
					double wallY = (mod1.y + mod2.y) / 2.0;
					Wall wall = new Wall(wallX, wallY);
					walls.add(wall);
				}
			}
		}
		if (walls.isEmpty())
		{
			return null;
		}
		return getTheWall(walls);
	}

	public static List<List<Module>> getComponents(Module[][] modules)
	{
		List<List<Module>> components = new ArrayList<List<Module>>();
		
		Stack<Module> stack = new Stack<>();
		
		while (nextMod(modules) != null)
		{
			List<Module> component = new ArrayList<Module>();
			stack.add(nextMod(modules));
			while (!stack.isEmpty())
			{
				Module curr = stack.pop();
				if (!curr.visited)
				{
					component.add(curr);
					curr.visited = true;
					List<Module> neighbors = curr.neighbors;
					for (Module neighbor : neighbors)
					{
						if (!neighbor.visited)
						{
							stack.add(neighbor);
						}
					}
				}
			}
			components.add(component);
		}
		return components;
	}
	
	public static Module nextMod(Module[][] modules)
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				if (!modules[i][j].visited)
				{
					return modules[i][j];
				}
			}
		}
		return null;
	}
	
	
}
	
