#!/usr/bin/perl -w
# 去重复字

use strict;
use warnings;

use Encode;
use Encode::CN;

if( scalar(@ARGV) <= 0 ){
    die "Error: Need File Name input\n";
}

for(@ARGV){
    &process_one($_);
}


sub process_one(){
    
    my $file_name = shift;
    
    open RED, "$file_name" or die "Error: open file error $@";
    my @all_lines = <RED>;
    chomp @all_lines;
    close RED;
    my %hash = ();

    
    for my $line (@all_lines){
        Encode::_utf8_on($line);
        for( my $index = 0; $index < length $line; $index++ ){
            my $one = substr $line, $index, 1;
            $hash{$one}++;
        }
    }
    
    my $output_file_name = $file_name;
    $output_file_name =~ s/\./_res\./g;
    
    open WRT, ">$output_file_name" or die "Error: open file error $@";
    print WRT keys %hash;    
    close WRT;

    print "\n";
    print STDERR "$file_name : ", scalar( keys %hash );
    print "\n";
}
