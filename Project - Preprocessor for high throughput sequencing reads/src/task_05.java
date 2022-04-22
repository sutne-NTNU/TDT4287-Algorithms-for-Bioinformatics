
/*
 * De-multiplex barcoded library
 * 
 * A common strategy for reducing sequencing costs when sequencing multiple samples is to use a
 * specific 3’ adapter sequence (barcode; see Figure 3 for an example) per sample, mix all sample
 * libraries and run a single sequencing reaction, and then use the sample-specific adapter sequence
 * (barcode) to identify (de-multiplex) which sample any given sequence belongs to. The file
 * MultiplexedSamples.gz contains the results of such a multiplex sequencing experiment.
 * 
 * Your tasks are: (1) to identify the barcodes (3’ adapters) used and thereby how many samples were
 * multiplexed, (2) identify how many sequences that were sequenced from each sample (3) identify
 * the sequence length distribution within each sample.
 * 
 * What is the most frequently occurring sequence within each sample?
 * 
 * HINT: All barcodes have the same length. Moreover, most barcodes in use are fairly short (4-8
 * characters).
 */
class Task_05
{
    public static void main(String[] args)
    {
        main();
    }



    static void main()
    {
        System.out.println("\nTASK 5 - TODO");
    }
}
