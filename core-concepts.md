PDX Core Data Model
===================

Definitions
-----------

The purpose of the PDX core data model is to caputure information about PDX models that are available at various resources / producers.  To that end, we have come up with a data model that attemtps to convert relevant information about PDX production and models into an informatic resource.  Following are definitions this project uses internally and are not meant to (necessarily) reflect the state of the world.

### Patient

Represents a human from whom a tumor (or a series of) was extracted.

- externalId: The original ID at the datasource providing the data of the Patient node
- sex: Male or Female
- race: The patient's physical characteristics such as skin, hair, or eye color
- ethnicity: The Patient's origin by ancestry e.g German or Spanish ancestry
- dataSource: The name of the datasource (this field is a copied from the associated ExternalDataSource node name field)
- externalDataSource : A relationship to an ExternalDataSource node - see node ExternalDataSource
- snapshots: Represents a collection of points in time when a tumor was extracted from a patient - see node PatientSnapshot

### PatientSnapshot
 
Represents a point in time when a tumor was extracted from a patient.

- patient : The patient from which this tumor was extracted
- age: The patient's age at the time of collection (binned to ten year bins)
- dateAtCollection: The date of collection of the tumour (this needs to be staged in months: 0, 6 months, 9months)
- treatmentNaive: Represents if the patient has had any prior treatments. Expected value of: yes or no or unknown
- samples: A collection of samples associated to patient snapshot - see Sample node
- treatments: A collection of treatments associated with current snapshot (and previous snapshots if available), if there were any treatment info the treatmentNaive can be infered to 'no' - see Treatment node

### Sample

Represents either a patient extracted tumor sample or a Xenograft tumor sample
    
- sampleToOntologyRelationShip: Represents the relationship between this sample and the related OntologyTerm - see  SampleToOntologyRelationShip node
- type: type of the tumour (ie: metastasis,  primary) - see node TumorType
- molecularCharacterizations: Represents a collection of the results of applying a specified technology to the biological sample in order to gain insight to the tumor's attributes - see node MolecularCharacterization
- patientSnapshot: Represents a point in time when a tumor was extracted from a patient - see node PatientSnapshot
- histology: represent a set of microscopic images representing the tumour - see Histology node
- sourceSampleId: The original ID at the datasource providing the data of the human Sample
- diagnosis: Histology classification term  provided by the data source (E.g., lung adenocarcinoma).  This is mapped to and OntologyTerm
- originTissue: Primary tissue from which the tumour orgininated (E.g., lung), this can be different from collection site when metastatic - see node Tissue
- sampleSite: Tissue from which the tumour was extracted (E.g., liver) - see node Tissue
- extractionMethod: Procedure which led to the tumour extraction (ie: biopsy, surgery)
- classification: Diagnostic classification of the cancer diagnosis progression
- normalTissue: Indicates if the tissue is a non-caancerous extraction.  A sample may or may not be cancerous (the tissue could have been collected from a healthy tissue for use as control to a cancer sampled).  Expected values: yes or no
- dataSource: The name of the datasource (this field is a copied from an associated ExternalDataSource node name field for query optimization, E.g., IRCC)

### ModelCreation

Represents the unique PDX model line (created from a patient tumour transplanted into one or more mice) where information about the process used to generate the first Xenograft is collected.

- externalId: The original ID at the datasource providing the data of the PDX model line ID (created from a unique patient tumour transplanted into a mouse)
- dataSource: The name of the datasource (this field is a copied from an associated ExternalDataSource node name field for query optimization, E.g., IRCC)
- qualityAssurance: Represents the method used to validate the samples in the model from the Xenograft correspond to the sample from the human  - see node QualityAssurance 
- sample: Human patient sample which was implanted in the mouse - see node Sample
- relatedSamples: Other samples from the same patient which have also been made into PDX models - see Sample node
- specimens: A collection of Xenografted mice that have participated in the line creation and characterisation in a meaninful way (E.g., Have been used for validation) - see node Specimen

### Specimen

Represents a Xenografted mouse that has participated in the line creation and characterisation in some meaninful way.  E.g., the specimen provided a tumor that was characterized and used as quality assurance or drug dosing data.

- externalId: The original ID at the datasource providing the data of the PDX specimen ID
- passage: The passage number of the specimen
- sample: Tumor sample extracted from the specimen for further characterisation or storage. see node Sample
- treatment: Drug dosing studies associated with specimen - see node Treatment


- implantationSite: The site of implantation of the tumour into the mouse/specimen (E.g., right flank). see node ImplantationSite
- implantationType: The type of implantation (E.g., subcutaneous) - see node ImplantationType
- hostStrain: The strain of mouse used as host for the sample - see node HostStrain

#### Host Strain

- name: The commononly used name of the strain. E.g., NOD scid gamma
- symbol: The official nomenclature of the strain, this should be the standard nomenclature for describing mouse strains E.g., NOD.Cg-Prkdc<scid>Il2rg<tm1Wjl/SzJ> http://www.jax.org/jax-mice-and-services/customer-support/technical-support/genetics-and-nomenclature
- description: Text describing the mouse strain
- url: The url where the host strain may be purchased (E.g., http://www.jax.org/strain/005557)

#### Implantationtype

The method of implanting the tumor to the mouse.  E.g., Orthotopic, Heterotopic, Subcutanteous (a type of Heterotopic implantation)

#### ImplantationSite

The location or organ where the tumor was implanted in the mouse.  E.g., right flank, lung

#### Treatment

Any drug / treatment regime the Xenograft was treated with.  A treatment has:

- Display of treatement information is ongoing - this will change soon

##### Treatement protocol

The specifics of drug(s) and timing of administering the drugs.

- Display of treatement protocol information is ongoing - this will change soon

### Molecular Characterization

Represents the results of applying a specified technology to the biological sample in order to gain insight to the tumor's attributes.

- platform: technical platform used to generate molecular characterisation.  E.g., targeted next generation sequencing - see node Platform 
- markerAssociations: A collection of the descriptions of the data associated with a particular molecular characterisation type (variation, expression, methylation, ...) - see node MarkerAssociation

### Marker

A marker represents a specific location on the _human_ genome that usually cooresponds to a gene

- symbol: HUGO nomenclature gene symbol (E.g., KRAS, https://www.genenames.org/cgi-bin/gene_symbol_report?hgnc_id=HGNC:6407) - The genes are validated based on this https://www.genenames.org/
- name: The full name of the gene (E.G., KRAS proto-oncogene, GTPase)
- hugoId: The HUGO accession ID (E.g., HGNC:6407)
- ensemblId: The ensembl accession ID (E.g., ENSG00000133703) - provided by HUGO
- entrezId: The entrez gene accession ID (E.G., 3845) - provided by HUGO
- prevSymbols: A collection of any previous symbols or names used for this marker (E.g., "Kirsten rat sarcoma viral oncogene homolog", KRAS2, "v-Ki-ras2 Kirsten rat sarcoma 2 viral oncogene homolog", "v-Ki-ras2 Kirsten rat sarcoma viral oncogene homolog") - provided by HUGO
- synonyms: A collection of synonyms used for this marker (E.g., KRAS1) - provided by HUGO

### Platform

Represents the technique used to produce the molecular characterization for a PDX specimen.  Associated with a platform are usually a number (hundreds / thousands) of Markers that the platform tests for.  For instance, a gene panel may test for 80 cancer genes.

- name: The name of the platform (E.g., NCI cancer panel)
- externalDataSource : A relationship to an ExternalDataSource node (E.g., PDMR) - see ExternalDataSource node
- platformAssociations: - A collection of associations to markers for which this platform normally characterizes - see PlatformAssociation node

### Marker Association

A relationship between a Marker and a specific *MolecularCharacterization*

- description: A descrition of the association
- marker: The associated Marker - see node Marker
- chromosome: The chromosome on which the marker is located.
- seqPosition: When supplied, in cases where there is only one position indicated by the data source, the location of the start of the mutation.
- refAllele: The reference sequence at the location(s) specified
- altAllele: The altered sequence at the location(s) specified
- consequence: Represents what effect the mutation had on the marker
- aminoAcidChange: Represents the amino acid change the mutation produced to the protein
- rsVariants: A SNP identifier of the location
- readDepth: The number of sequence reads used to call the event
- alleleFrequency: The frequence of the event (Specified as a percentage between 0 and 1)
- refAssembly: The human reference sequence used to map the read (E.g., NCBI38)
- seqStartPosition: The location of the start of the mutation
- seqEndPosition: The location of the end of the mutation
- strand: The strand corresponding to the direction of the marker in the genome. Expected values: 1 or -1)
- cdsChange: The DNA representation of the codon changed by the event
- type: The type of mutation caused by this variant. E.g., stop, missense
- annotation: Source specifc annotation. E.g., Driver mutation

### ExternalDataSource

- name: The name of the data source (E.g., The Jackson Laboratory)
- abbreviation: A short identifier for the datasource (E.g., JAX)
- description: A textual of the datasource
- dateLastUpdated: currently unused

### Histology

A collection of images associated used to characterize a tumor

- images: A collection of images associated used to charachterize a tumor

### Image

An image used to characterize a tumor

- url: The URL of the image
- description: A discription of the image

### Ontology Term

A term used to standardize and harmonize data

- url: The URL of the term in the Ontology Lookup Service
- label: The name of the term (E.g., Adult glioblastoma)
- directMappedSamplesNumber: A count of the samples associated to this term directly
- indirectMappedSamplesNumber: A count of the samples that are associated to children ontolgy terms of this term
- synonyms: A collection of synonyms for this term
- subclassOf: Represents a collection of parent terms of this term
- mappedTo: A collection of Human tissue Sample nodes with which this term is directly associated.

### PdxPassage

Deprecated

### QualityAssurance

Represents a procedure performed to correlate a tumor sample taken from a human patient to a xenograft sampe taken from a mouse (perhaps after multiple passages)

- technology: The tehnology used to perform the procedure
- description: A textual description of the technology used
- validationTechniques: A standardized representation of the technique used
- passages: A textual description representing which passages were validated

### Response

Represents the activity of the tumor sample in response to a treatment.  This is also used to represent control responses where a placebo treatment may or may not have been administered.

- description: A RECIST criteria description of the response.  Expected values: Progressive, Stable, ...
- treatment: The treatment used to produce this response.  See node Treatment

### Tissue

A human tissue from which a sample was collected.

- name: The name of the tissue

### Treatment

Represents a specific administration of a TreatmentProtocol to a human or xenograft.

- therapy: The name of the treatment given when patient history is not detailed, but provided.
- treatmentProtocol: A specific regime of a drug applied to a sample at specific intervales. see node TreatmentProtocol
- snapshot: When supplied, indicates to which HUMAN patient tumor at which time this treatment protocal was applied. See node PatientSnapshot
- specimen: When supplied, indicates to which XENOGRAFT tumor this treatment protocal was applied. See node Specimen
- response: Represents the tumor's response to the treatment. see node Response

### TreatmentProtocol

Represents a standardized drug treatment protocol used in a research setting.

- regime: The name of the treatment regime applied to the sample.  This corresponds to a specific drug (or combination)
 applied at specific doses and times.

### ValidationTechniques

An enumeration of all possible validation techniques used in the validation process. The current list is:
```
    NOT_SPECIFIED("Not specified")
    VALIDATION("Validation")
    FINGERPRINT("Fingerprint")
    FINGERPRINT_HISTOLOGY("Histology/STR")
    HEALTH_CHECK("Health-check")
```



Concepts
--------

### Node

Usually refers to a specific type of data in the graph.  For instance a Patient Node refers to a record in the database representing a specific patient.

### 


Technical decisions
-------------------

Several decisions have been made with respect to the data model that have trade offs.

1.  We used the Neo4J graph database to model the database
2.  We used Java and the Spring Framework to build the web application and loaders
3.  

