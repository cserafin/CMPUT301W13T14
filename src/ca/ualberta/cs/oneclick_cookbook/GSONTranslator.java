package ca.ualberta.cs.oneclick_cookbook;

// TODO Implement this class

// This class should essentially wrap the GSON functions, as
// well as being the interface to the network
public class GSONTranslator<T> {
	String _index;
	String _type;
    String _id;
    int _version;
    boolean exists;
    T _source;
    double max_score;
    public T getSource() {
        return _source;
    }
}
