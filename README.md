This is the command-line version of the ncISO utility for creating ISO metadata and ACDD rubric HTML files from a netCDF data source.

The command line options are:

–Xms1024m and –Xmx1024m: Standard java elements for specifying the amount of memory to allocate to the ncISO utility. In this case 1024 megaBytes are specified for initial and maximum memory.


–ts THREDDS_CATALOG_URL: specifies the URL of the THREDDS catalog to process.


–num N: specifies the number of datasets to process per branch. Specifying a small number of datasets/branch, as in this case, results in a fast sample scan that is representative in THREDDS catalogs with generally homogeneous content in each branch. 

Specify a large number for a translation of all content.


–depth 20: limits the crawlers descent into the catalog.


–iso: signals to the crawler to generate ISO.


–waf ROOT_WAF_FOLDER: signals the crawler to dump files to a flat WAF structure.


–custom: signals to the crawler to translate the NCML using a custom stylesheet.


–xslt: XSLT_FILENAME located in an xslt subfolder.


Example: 

Crawl small example NOAA catalog and generate metadata: 

java -Xms1024m -Xmx1024m -jar ncISO-2.3.5.jar -ts http://ferret.pmel.noaa.gov/pmel/thredds/carbontracker.xml -num 1 -depth 20 -iso true
