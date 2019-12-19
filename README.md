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

java -Xms1024m -Xmx1024m -jar ncISO-2.3.6.jar -ts https://ferret.pmel.noaa.gov/pmel/thredds/carbontracker.xml -num 1 -depth 20 -iso true

Try:

java -Xms1024m -Xmx1024m -jar ncISO-2.3.6.jar -ts http://amb6400b.stccmop.org:8080/thredds/forecast_model_data.xml -num 125 -depth 100 -iso true

to see the new for 2.3.6 individual &lt;gmd:keyword&gt elements from the comma-separated GCMD keywords in the keywords and the netCDF source attribute put into the &lt;gmi:LE_ProcessStep&gt;&lt;gmd:source&gt;&lt;gmi:LE_Source&gt;&lt;gmd:description&gt;.

#### Legal Disclaimer
*This repository is a software product and is not official communication
of the National Oceanic and Atmospheric Administration (NOAA), or the
United States Department of Commerce (DOC). All NOAA GitHub project
code is provided on an 'as is' basis and the user assumes responsibility
for its use. Any claims against the DOC or DOC bureaus stemming from
the use of this GitHub project will be governed by all applicable Federal
law. Any reference to specific commercial products, processes, or services
by service mark, trademark, manufacturer, or otherwise, does not constitute
or imply their endorsement, recommendation, or favoring by the DOC.
The DOC seal and logo, or the seal and logo of a DOC bureau, shall not
be used in any manner to imply endorsement of any commercial product
or activity by the DOC or the United States Government.*

