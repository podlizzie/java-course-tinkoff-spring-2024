/*
 * This file is generated by jOOQ.
 */

package edu.java.domain.jooq.tables;

import edu.java.domain.jooq.DefaultSchema;
import edu.java.domain.jooq.Keys;
import edu.java.domain.jooq.tables.records.GithubBranchesRecord;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import javax.annotation.processing.Generated;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Function2;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Records;
import org.jooq.Row2;
import org.jooq.Schema;
import org.jooq.SelectField;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "https://www.jooq.org",
        "jOOQ version:3.18.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes", "this-escape"})
public class GithubBranches extends TableImpl<GithubBranchesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>GITHUB_BRANCHES</code>
     */
    public static final GithubBranches GITHUB_BRANCHES = new GithubBranches();

    /**
     * The class holding records for this type
     */
    @Override
    @NotNull
    public Class<GithubBranchesRecord> getRecordType() {
        return GithubBranchesRecord.class;
    }

    /**
     * The column <code>GITHUB_BRANCHES.LINK_ID</code>.
     */
    public final TableField<GithubBranchesRecord, Long> LINK_ID =
        createField(DSL.name("LINK_ID"), SQLDataType.BIGINT, this, "");

    /**
     * The column <code>GITHUB_BRANCHES.BRANCHES</code>.
     */
    public final TableField<GithubBranchesRecord, String[]> BRANCHES =
        createField(DSL.name("BRANCHES"), SQLDataType.VARCHAR(1000000000).nullable(false).array(), this, "");

    private GithubBranches(Name alias, Table<GithubBranchesRecord> aliased) {
        this(alias, aliased, null);
    }

    private GithubBranches(Name alias, Table<GithubBranchesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>GITHUB_BRANCHES</code> table reference
     */
    public GithubBranches(String alias) {
        this(DSL.name(alias), GITHUB_BRANCHES);
    }

    /**
     * Create an aliased <code>GITHUB_BRANCHES</code> table reference
     */
    public GithubBranches(Name alias) {
        this(alias, GITHUB_BRANCHES);
    }

    /**
     * Create a <code>GITHUB_BRANCHES</code> table reference
     */
    public GithubBranches() {
        this(DSL.name("GITHUB_BRANCHES"), null);
    }

    public <O extends Record> GithubBranches(Table<O> child, ForeignKey<O, GithubBranchesRecord> key) {
        super(child, key, GITHUB_BRANCHES);
    }

    @Override
    @Nullable
    public Schema getSchema() {
        return aliased() ? null : DefaultSchema.DEFAULT_SCHEMA;
    }

    @Override
    @NotNull
    public List<ForeignKey<GithubBranchesRecord, ?>> getReferences() {
        return Arrays.asList(Keys.CONSTRAINT_5);
    }

    private transient Links _links;

    /**
     * Get the implicit join path to the <code>PUBLIC.LINKS</code> table.
     */
    public Links links() {
        if (_links == null) {
            _links = new Links(this, Keys.CONSTRAINT_5);
        }

        return _links;
    }

    @Override
    @NotNull
    public GithubBranches as(String alias) {
        return new GithubBranches(DSL.name(alias), this);
    }

    @Override
    @NotNull
    public GithubBranches as(Name alias) {
        return new GithubBranches(alias, this);
    }

    @Override
    @NotNull
    public GithubBranches as(Table<?> alias) {
        return new GithubBranches(alias.getQualifiedName(), this);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public GithubBranches rename(String name) {
        return new GithubBranches(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public GithubBranches rename(Name name) {
        return new GithubBranches(name, null);
    }

    /**
     * Rename this table
     */
    @Override
    @NotNull
    public GithubBranches rename(Table<?> name) {
        return new GithubBranches(name.getQualifiedName(), null);
    }

    // -------------------------------------------------------------------------
    // Row2 type methods
    // -------------------------------------------------------------------------

    @Override
    @NotNull
    public Row2<Long, String[]> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Function)}.
     */
    public <U> SelectField<U> mapping(Function2<? super Long, ? super String[], ? extends U> from) {
        return convertFrom(Records.mapping(from));
    }

    /**
     * Convenience mapping calling {@link SelectField#convertFrom(Class,
     * Function)}.
     */
    public <U> SelectField<U> mapping(Class<U> toType, Function2<? super Long, ? super String[], ? extends U> from) {
        return convertFrom(toType, Records.mapping(from));
    }
}
