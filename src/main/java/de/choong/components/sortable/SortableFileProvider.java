package de.choong.components.sortable;

import java.io.File;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.extensions.markup.html.repeater.data.sort.SortOrder;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class SortableFileProvider extends SortableDataProvider<File, String> {

    private static final long serialVersionUID = 4702733050594337906L;

    private List<File> files;

    public SortableFileProvider(IModel<String> path) {
        super();
        this.setSort("name", SortOrder.ASCENDING);
        files = Arrays.asList(new File(path.getObject()).listFiles());
    }

    @Override
    public Iterator<? extends File> iterator(long first, long count) {
        // TODO Sort
        return files.subList((int) first, (int) (first + count)).iterator();
    }

    @Override
    public long size() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public IModel<File> model(File object) {
        return Model.of(object);
    }

}
